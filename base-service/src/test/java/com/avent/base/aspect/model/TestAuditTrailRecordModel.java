package com.avent.base.aspect.model;

import com.avent.base.model.AuditTrailRecord;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestAuditTrailRecordModel implements AuditTrailRecord {

    private String data1;
    private String data2;
}
