package com.demotest;

import com.demotest.service.TestService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.Cookie;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class DemoTestApplicationTests {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private TestService testService;

    @BeforeAll
    public static void beforeAll() {
        System.out.println("before all");
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("before each");
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void Test1() {
        System.out.println("test 1+1=2");
        // 断言结果为 2
        Assertions.assertEquals(2, 1 + 1);
    }

    @Test
    public void Test2() {
        System.out.println("test 2+2=4");
        // 断言结果为 4
        Assertions.assertEquals(4, 2 + 2);
    }

    @Test
    public void testGetRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/hello?name={name}","luhang"))
                // 输出响应结果
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testPostRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/{id}", 1))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testUploadRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.multipart("/fileUpload")
                .file("file", "file contents,test file upload.".getBytes(StandardCharsets.UTF_8)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testParamRequest() throws Exception {
        // 请求 hello 接口，name 为参数名，luhang admin 为参数值，值可以填写单个或者多个（多个得到的值会以","隔开）
        mockMvc.perform(MockMvcRequestBuilders.get("/hello").param("name", "luhang", "admin"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testParamsRequest() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("name", "luhang");
        params.add("age", "24");
        params.add("job", "java");
        mockMvc.perform(MockMvcRequestBuilders.get("/saveUser").params(params))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testBodyRequest() throws Exception {
        String jsonStr = "{\"username\":\"luhang\",\"passwd\":\"4065e046d95dda3e8eaa7e7b5e18bd7cb98f1353\",\"status\":\"1\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/saveUser")
                        .content(jsonStr.getBytes())
                        .contentType(MediaType.APPLICATION_JSON) // 设置请求的Content-Type
                        .accept(MediaType.APPLICATION_JSON) // 设置返回格式为JSON
                        .header("Content-Type", "application/json")) // 模拟HTTP请求头
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testSessionCookieRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/index").sessionAttr("name", "luhang").cookie(new Cookie("name", "luhang")))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public  void testAssertionResponse() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/hello?name={name}","luhang"))
//                // 断言返回内容 HTTP Status 为 200
//                .andExpect(MockMvcResultMatchers.status().isOk());

//        mockMvc.perform(MockMvcRequestBuilders.get("/user/{id}", "666"))
//                // 断言返回内容是 application/json
//                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

//        mockMvc.perform(MockMvcRequestBuilders.get("/user/{id}", "666"))
//                // 断言返回 JSON 数据中 data 值的内容为 userId: 666
//                .andExpect(MockMvcResultMatchers.jsonPath("$.data").value("userId: 666"));

//        mockMvc.perform(MockMvcRequestBuilders.post("/index"))
//                //断言返回视图为 /index.html
//                .andExpect(MockMvcResultMatchers.view().name("/index.html"));

//        mockMvc.perform(MockMvcRequestBuilders.get("/index"))
//                // 断言 model size 为 1
//                .andExpect(MockMvcResultMatchers.model().size(1))
//                // 断言 model 中存在 name 属性
//                .andExpect(MockMvcResultMatchers.model().attributeExists("name"))
//                // 断言 model 中 name 属性值为 luhang
//                .andExpect(MockMvcResultMatchers.model().attribute("name", "luhang"));

//        mockMvc.perform(MockMvcRequestBuilders.get("/index"))
//                // 断言请求路径转发至 /index.html
//                .andExpect(MockMvcResultMatchers.forwardedUrl("/index.html"));

//        mockMvc.perform(MockMvcRequestBuilders.get("/home"))
//                // 断言请求路径重定向至 /index.html
//                .andExpect(MockMvcResultMatchers.redirectedUrl("/index.html"));

//        mockMvc.perform(MockMvcRequestBuilders.get("/hello?name={name}","luhang"))
//                // 断言返回正文为 Hello! Welcome luhang to test!
//                .andExpect(MockMvcResultMatchers.content().string("Hello! Welcome luhang to test!"));

//        mockMvc.perform(MockMvcRequestBuilders.get("/xml"))
//                // 断言返回正文是 xml 并且值为 <body>body</body>
//                .andExpect(MockMvcResultMatchers.content().xml("<body>body</body>"));

        mockMvc.perform(MockMvcRequestBuilders.get("/json"))
                // 断言返回正文是 json 并且值为 {"name":  "luhang"}
                .andExpect(MockMvcResultMatchers.content().json("{\"name\": \"luhang\"}"));
    }

    @Test
    public void testAssertionService() {
        List<Map<String, Object>> list = testService.getUserByName("大BOSS");
        // 断言返回 name 为 大BOSS
        Assertions.assertEquals("大BOSS", list.get(0).get("name").toString(), "用户名为：大BOSS");
    }

    @Test
    @Transactional // 测试完成后进行回滚
    public void testTransactionalService() {
        Map<String, Object> params = new HashMap<>();
        params.put("name", "admin");
        params.put("age", 20);
        params.put("email", "admin@163.com");
        params.put("manager_id", 1);
        testService.save(params);
        List<Map<String, Object>> list = testService.getUserByName("admin");
        Assertions.assertEquals("admin", list.get(0).get("name").toString(), "已添加用户：admin");
    }

    @AfterEach
    public void afterEach() {
        System.out.println("after each");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("after all");
    }
}
