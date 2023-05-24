////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

/*
 * Copyright © 2021 Unified Social, Inc.
 * 180 Madison Avenue, 23rd Floor, New York, NY 10016, U.S.A.
 * All rights reserved.
 *
 * This software (the "Software") is provided pursuant to the license agreement you entered into with Unified Social,
 * Inc. (the "License Agreement").  The Software is the confidential and proprietary information of Unified Social,
 * Inc., and you shall use it only in accordance with the terms and conditions of the License Agreement.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND "AS AVAILABLE."  UNIFIED SOCIAL, INC. MAKES NO WARRANTIES OF ANY KIND, WHETHER
 * EXPRESS OR IMPLIED, INCLUDING, BUT NOT LIMITED TO THE IMPLIED WARRANTIES AND CONDITIONS OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT.
 */

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

package hoangnd.learn.spring.entity;

import java.io.Serializable;
import java.time.LocalDateTime;


import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Rule.
 */
@Getter
@Entity
@Table(name = "RULES")
@Builder(toBuilder = true, builderClassName = "RuleBuilder")
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PACKAGE)
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Rule implements Serializable {
    private static final long serialVersionUID = 51908156108226248L;

    /**
     * Resource Type.
     */
    public enum ResourceType {
        DRL
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "KNOWLEDGE_BASE_ID", referencedColumnName = "ID")
    private KnowledgeBase knowledgeBase;

    @Column(name = "PACKAGE_NAME", nullable = false, length = 256)
    private String packageName;

    @Column(name = "RESOURCE_NAME", nullable = false, length = 256)
    private String resourceName;

    @Enumerated(EnumType.STRING)
    @Column(name = "RESOURCE_TYPE", nullable = false, length = 20)
    private ResourceType resourceType;

    @Column(name = "RESOURCE_CONTENTS", length = 4000)
    private String resourceContents;

    @Column(name = "IS_ENABLED")
    private Boolean enabled;

    @Lob
    @Column(name = "RESOURCE_DATA", columnDefinition = "bytea")
    private byte[] resourceData;

    @Column(nullable = false, name = "VERSION")
    @Version
    private int version;

    @Column(name = "MODIFIED_DATE", insertable = false)
    private LocalDateTime modifiedDate;

    @Column(name = "CREATED_DATE", insertable = false, updatable = false)
    private LocalDateTime createdDate;

    public byte[] getResourceData () {
        return this.resourceData == null ? null : resourceData.clone();
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class RuleBuilder {
        public RuleBuilder resourceData (byte[] resourceData) {
            this.resourceData = resourceData == null ? null : resourceData.clone();
            return this;
        }
    }

}
