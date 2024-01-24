package blue.builder.me.common.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


/**
 * regDttm(등록일시), updDttm(수정일시) 공통 컬럼 위한 entity
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)  // JPA Auditing(시간 관련 값 감시하고 값을 넣어주는 기능) 적용
public abstract class BaseTime {
    @CreatedDate
    @Column(columnDefinition = "timestamp without time zone")
    private LocalDateTime regDttm;
    @LastModifiedDate
    @Column(columnDefinition = "timestamp without time zone")
    private LocalDateTime updDttm;
}
