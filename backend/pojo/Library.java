package com.example.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 文献库数据
 * @TableName sys_library
 */
@TableName(value ="sys_library")
@Data
public class Library implements Serializable {
    /**
     * 文献库id
     */
    @TableId(type = IdType.AUTO)
    private Integer libraryId;

    /**
     * 创建者id
     */
    private Integer userId;

    /**
     * 文献库名
     */
    private String topic;

    /**
     * 文献库描述
     */
    private String des;

    /**
     * 是否公开
     */
    private Integer isPublic;

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
        Library other = (Library) that;
        return (this.getLibraryId() == null ? other.getLibraryId() == null : this.getLibraryId().equals(other.getLibraryId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getTopic() == null ? other.getTopic() == null : this.getTopic().equals(other.getTopic()))
            && (this.getDes() == null ? other.getDes() == null : this.getDes().equals(other.getDes()))
            && (this.getIsPublic() == null ? other.getIsPublic() == null : this.getIsPublic().equals(other.getIsPublic()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getLibraryId() == null) ? 0 : getLibraryId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getTopic() == null) ? 0 : getTopic().hashCode());
        result = prime * result + ((getDes() == null) ? 0 : getDes().hashCode());
        result = prime * result + ((getIsPublic() == null) ? 0 : getIsPublic().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", libraryId=").append(libraryId);
        sb.append(", userId=").append(userId);
        sb.append(", topic=").append(topic);
        sb.append(", des=").append(des);
        sb.append(", isPublic=").append(isPublic);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}