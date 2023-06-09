package com.example.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 文献库评论区
 * @TableName sys_libcomment
 */
@TableName(value ="sys_libcomment")
@Data
public class Libcomment implements Serializable {
    /**
     * 文献库评论id
     */
    @TableId(type = IdType.AUTO)
    private Integer libcomId;

    /**
     * 发表评论的用户
     */
    private String username;

    /**
     * 文献库id
     */
    private Integer libraryId;

    /**
     * 评论内容
     */
    private String content;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Libcomment other = (Libcomment) that;
        return (this.getLibcomId() == null ? other.getLibcomId() == null : this.getLibcomId().equals(other.getLibcomId()))
            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
            && (this.getLibraryId() == null ? other.getLibraryId() == null : this.getLibraryId().equals(other.getLibraryId()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getLibcomId() == null) ? 0 : getLibcomId().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getLibraryId() == null) ? 0 : getLibraryId().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", libcomId=").append(libcomId);
        sb.append(", username=").append(username);
        sb.append(", libraryId=").append(libraryId);
        sb.append(", content=").append(content);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}