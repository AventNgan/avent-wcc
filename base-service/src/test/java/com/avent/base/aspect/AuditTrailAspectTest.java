package com.avent.base.aspect;

import com.avent.audittrail.repository.AuditTrailDao;
import com.avent.base.aspect.annotation.AuditTrail;
import com.avent.base.entity.AuditTrailEntity;
import com.avent.base.util.RequestUtil;
import com.avent.location.model.request.PostcodeUpdateRequestModel;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import com.avent.base.aspect.model.TestAuditTrailRecordModel;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AuditTrailAspectTest {

    @InjectMocks
    private AuditTrailAspect auditTrailAspect;

    @Mock
    private AuditTrailDao auditTrailDao;

    @Mock
    private JoinPoint joinPoint;

    @Mock
    private MethodSignature methodSignature;

    @Mock
    private Method method;


    @Captor
    private ArgumentCaptor<AuditTrailEntity> auditTrailEntityCaptor;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldAuditTrailAfterReturning() throws Exception {
        PostcodeUpdateRequestModel auditTrailRecord = new PostcodeUpdateRequestModel();
        auditTrailRecord.setPostcode("postcode");
        auditTrailRecord.setLongitude(1.0);
        auditTrailRecord.setLatitude(1.0);

        Object[] args = new Object[]{auditTrailRecord};

        when(joinPoint.getArgs()).thenReturn(args);
        when(joinPoint.getSignature()).thenReturn(methodSignature);
        when(methodSignature.getMethod()).thenReturn(method);
        AuditTrail auditTrail = mock(AuditTrail.class);
        when(method.getAnnotation(AuditTrail.class)).thenReturn(new AuditTrail(){

            @Override
            public Class<? extends Annotation> annotationType() {
                return AuditTrail.class;
            }

            @Override
            public String action() {
                return "test";
            }
        });

        try (MockedStatic<SecurityContextHolder> mocked = Mockito.mockStatic(SecurityContextHolder.class)) {
            mocked.when(() -> SecurityContextHolder.getContext()).thenReturn(getSecurityContext());
            auditTrailAspect.auditTrailAfterReturning(joinPoint, auditTrail, auditTrailRecord);

        }


        verify(auditTrailDao, times(1)).save(auditTrailEntityCaptor.capture());
        AuditTrailEntity savedEntity = auditTrailEntityCaptor.getValue();
        assertEquals("test: {\"postcode\":\"postcode\",\"longitude\":1.0,\"latitude\":1.0} | ", savedEntity.getDetails());
        assertEquals("TestUser", savedEntity.getUsername());
    }

    @Test
    public void shouldNotAuditTrailWhenNoAuditTrailRecord() throws Exception {
        Object[] args = new Object[]{"test"};

        when(joinPoint.getArgs()).thenReturn(args);
        when(joinPoint.getSignature()).thenReturn(methodSignature);
        when(methodSignature.getMethod()).thenReturn(method);
        AuditTrail auditTrail = mock(AuditTrail.class);
        when(method.getAnnotation(AuditTrail.class)).thenReturn(null);
        auditTrailAspect.auditTrailAfterReturning(joinPoint, auditTrail, "test");

        verify(auditTrailDao, never()).save(any(AuditTrailEntity.class));
    }

    private SecurityContext getSecurityContext(){
        return new SecurityContext() {
            @Override
            public Authentication getAuthentication() {
                return getMockAuthentication();
            }

            @Override
            public void setAuthentication(Authentication authentication) {

            }
        };
    }

    private Authentication getMockAuthentication(){
        return new Authentication() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public Object getCredentials() {
                return null;
            }

            @Override
            public Object getDetails() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                return "TestUser";
            }

            @Override
            public boolean isAuthenticated() {
                return false;
            }

            @Override
            public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

            }

            @Override
            public String getName() {
                return null;
            }
        };
    }
}
