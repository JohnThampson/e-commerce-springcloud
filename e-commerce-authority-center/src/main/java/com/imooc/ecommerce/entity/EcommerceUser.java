package com.imooc.ecommerce.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * <h1>用户表实体类数据</h1>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)  // 在操作数据表时让JPA辅助监听,实现主动创建或更新createTime和updateTime
@Table(name = "t_ecommerce_user")
public class EcommerceUser implements Serializable {

    /** 自增主键 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /** 用户名 */
    @Column(name = "username", nullable = false)
    private String username;;

    /** MD5 密码 */
    @Column(name = "password", nullable = false)
    private String password;

    /** 额外的信息, json字符串存储 */
    @Column(name = "extra_info", nullable = false)
    private String extraInfo;

    /** 创建时间 */
    @CreatedDate
    @Column(name = "create_time", nullable = false)
    private Date createTime;

    /** 更新时间 */
    @LastModifiedDate
    @Column(name = "update_time", nullable = false)
    private Date update_time;

}
