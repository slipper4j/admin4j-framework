package com.admin4j.framework.lock.configuration;

import com.admin4j.common.pojo.IResponse;
import com.admin4j.common.pojo.ResponseEnum;
import com.admin4j.common.pojo.SimpleResponse;
import com.admin4j.framework.lock.exception.DistributedLockException;
import com.admin4j.framework.lock.exception.IdempotentException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 *
 * @author andanyang
 */
@RestControllerAdvice
@Slf4j
@ConditionalOnClass(SimpleResponse.class)
public class DLockGlobalExceptionHandler {


    /**
     * 捕捉DistributedLockException 全局异常
     *
     * @param e
     * @return 前端返回值
     */
    @ExceptionHandler(DistributedLockException.class)
    public ResponseEntity<IResponse> distributedLockException(DistributedLockException e) {
        log.error("distributedLockException：" + e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(SimpleResponse.of(ResponseEnum.ERROR_D_LOCK.getCode(), e.getMessage()));
    }

    @ExceptionHandler(IdempotentException.class)
    public ResponseEntity<IResponse> idempotentException(IdempotentException e) {
        log.error("idempotentException：" + e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(SimpleResponse.of(ResponseEnum.ERROR_D_IDEMPOTENT.getCode(), e.getMessage()));
    }
}
