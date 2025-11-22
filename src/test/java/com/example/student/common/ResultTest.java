package com.example.student.common;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Result类单元测试
 */
public class ResultTest {

    @Test
    public void testSuccessWithoutData() {
        Result<String> result = Result.success();
        assertEquals(200, result.getCode());
        assertEquals("操作成功", result.getMessage());
        assertNull(result.getData());
    }

    @Test
    public void testSuccessWithData() {
        String testData = "测试数据";
        Result<String> result = Result.success(testData);
        assertEquals(200, result.getCode());
        assertEquals("操作成功", result.getMessage());
        assertEquals(testData, result.getData());
    }

    @Test
    public void testSuccessWithCustomMessage() {
        String message = "自定义成功消息";
        String testData = "测试数据";
        Result<String> result = Result.success(message, testData);
        assertEquals(200, result.getCode());
        assertEquals(message, result.getMessage());
        assertEquals(testData, result.getData());
    }

    @Test
    public void testErrorWithMessage() {
        String errorMessage = "错误消息";
        Result<String> result = Result.error(errorMessage);
        assertEquals(500, result.getCode());
        assertEquals(errorMessage, result.getMessage());
        assertNull(result.getData());
    }

    @Test
    public void testErrorWithCodeAndMessage() {
        Integer errorCode = 404;
        String errorMessage = "未找到资源";
        Result<String> result = Result.error(errorCode, errorMessage);
        assertEquals(errorCode, result.getCode());
        assertEquals(errorMessage, result.getMessage());
        assertNull(result.getData());
    }
}
