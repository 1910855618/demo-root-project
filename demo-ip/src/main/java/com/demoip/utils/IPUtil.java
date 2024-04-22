package com.demoip.utils;

import lombok.extern.log4j.Log4j2;
import org.lionsoul.ip2region.xdb.Searcher;
import org.springframework.core.io.ClassPathResource;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.*;
import java.util.regex.Pattern;

/**
 * IP 城市定位
 *
 * @author luhang
 */
@Log4j2
public class IPUtil {
    // 将整个xdb文件加载到内存中(11M左右), 此种创建方式支持多线程, 因此只需要加载一次
    private final static Searcher SEARCHER;
    private static final String XDB_PATH = "static/xdb/ip2region.xdb";

    static {
        try {
            ClassPathResource resource = new ClassPathResource(XDB_PATH);
            // 获取真实文件路径
            String path = resource.getURL().getPath();
            byte[] cBuff = Searcher.loadContentFromFile(path);
            SEARCHER = Searcher.newWithBuffer(cBuff);
        } catch (Exception e) {
            log.error("初始化 ip2region.xdb 文件失败, 报错信息: [{}]", e.getMessage(), e);
            throw new RuntimeException("ip2region.xdb 文件加载失败!");
        }
    }

    /**
     * 解析 IP 地址
     *
     * @param ipStr 字符串类型 ip 例: 192.168.0.1
     * @return 返回结果形式 [国家, 区域, 省份, 城市, ISP] 例 [中国, 0, 湖南省, 长沙市, 电信]
     */
    public static List<String> parse(String ipStr) {
        return parse(ipStr, null);
    }

    /**
     * 自定义解析 ip 地址
     *
     * @param ipStr ip 字符串类型 ip
     * @param index 想要获取的区间 例如: 只想获取 省,市 index = [2, 3]
     * @return 返回结果例 [湖南省, 长沙市]
     */
    public static List<String> parse(String ipStr, int[] index) {
        try {
            long ip = Searcher.checkIP(ipStr);
            return parse(ip, index);
        } catch (Exception e) {
            log.error("ip 解析为 long 错误, ipStr: [{}], 错误信息: [{}]", ipStr, e.getMessage(), e);
            throw new RuntimeException("IP 解析失败!");
        }
    }

    /**
     * 自定义解析ip地址
     *
     * @param ip ip Long 类型 ip
     * @param index 想要获取的区间 例如: 只想获取 省,市 index = [2,3]
     * @return 返回结果例 [湖南省, 长沙市]
     */
    public static List<String> parse(Long ip, int[] index) {
        //获取xdb文件资源
        List<String> regionList = new ArrayList<>();
        try {
            String region = SEARCHER.search(ip);
            String[] split = region.split("\\|");
            if (index == null) {
                regionList = Arrays.asList(split);
            } else {
                for (int i : index) {
                    regionList.add(split[i]);
                }
            }
            //关闭资源
            SEARCHER.close();
        } catch (Exception e) {
            log.error("根据 ip 解析地址失败, ip: [{}], index[{}], 报错信息: [{}]", ip, index, e.getMessage(), e);
            throw new RuntimeException("IP 地址解析失败!");
        }
        return regionList;
    }

    /**
     * 获取客户端 IP
     *
     * @param request 请求
     * @return 客户端 IP
     */
    public static String getClientIP(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (Optional.ofNullable(ip).orElse("").isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("x-forwarded-for");
        }
        if (Optional.ofNullable(ip).orElse("").isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (Optional.ofNullable(ip).orElse("").isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (Optional.ofNullable(ip).orElse("").isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (Optional.ofNullable(ip).orElse("").isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (Optional.ofNullable(ip).orElse("").isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if (Pattern.matches("^(127\\.0\\.0\\.1|0:0:0:0:0:0:0:1)$", ip)) {
                //根据网卡取本机配置的IP
                InetAddress inet;
                try {
                    inet = getLocalHostExactAddress();
                } catch (Exception e) {
                    log.error("获取客户端 IP 失败, 错误信息: [{}]:", e.getMessage(), e);
                    throw new RuntimeException("获取客户端 IP 失败!");
                }
                ip = inet.getHostAddress();
            }
        }
        return ip;
    }

    /**
     * 本地主机确切 IP
     *
     * @return InetAddress
     */
    private static InetAddress getLocalHostExactAddress() throws UnknownHostException, SocketException {
        // 声明一个变量用于存储可能的本地地址
        InetAddress candidateAddress = null;
        // 获取所有网络接口
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        while (networkInterfaces.hasMoreElements()) {
            // 获取一个网络接口
            NetworkInterface networkInterface = networkInterfaces.nextElement();
            // 获取该网络接口的所有InetAddress
            for (InetAddress inetAddr : Collections.list(networkInterface.getInetAddresses())) {
                // 如果不是回环地址
                if (!inetAddr.isLoopbackAddress()) {
                    // 如果是站点本地地址，直接返回
                    if (inetAddr.isSiteLocalAddress()) {
                        return inetAddr;
                    }
                    // 如果是非站点本地地址，则将其设为候选地址
                    if (candidateAddress == null) {
                        candidateAddress = inetAddr;
                    }
                }
            }
        }
        // 如果没有找到站点本地地址，则返回候选地址，如果候选地址也为空，则返回本地主机地址
        return candidateAddress == null ? InetAddress.getLocalHost() : candidateAddress;
    }
}
