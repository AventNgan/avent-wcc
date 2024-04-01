package com.avent.base.aspect;

import com.avent.audittrail.repository.AuditTrailDao;
import com.avent.base.aspect.annotation.AuditTrail;
import com.avent.base.entity.AuditTrailEntity;
import com.avent.base.model.AuditTrailRecord;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@Aspect
@RequiredArgsConstructor
public class AuditTrailAspect {

    private final AuditTrailDao auditTrailDao;

    @AfterReturning(value = "@annotation(annotation)", returning = "result")
    public void auditTrailAfterReturning(JoinPoint joinPoint, AuditTrail annotation, Object result) throws JsonProcessingException {
        String details = "";
        for (Object argument : joinPoint.getArgs()) {
            if (argument instanceof AuditTrailRecord) {
                details += new ObjectMapper().writeValueAsString(argument) + " | ";
            }

        }

        AuditTrail auditTrail = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(AuditTrail.class);
        if (auditTrail != null) {
            String action = auditTrail.action();

            auditTrailDao.save(new AuditTrailEntity(String.format("%s: %s", action, details), SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString()));
        }


    }

}
