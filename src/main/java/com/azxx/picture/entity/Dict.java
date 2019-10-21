package com.azxx.picture.entity;

import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Table;
import java.util.Objects;

@NameStyle(Style.normal)
@Table(name = "emp_dict")
public class Dict {

    /**主键*/
    private Integer id;

    /**字典名称*/
    private String name ;

    /**字典id*/
    private String dictId ;

    /**字典类型*/
    private String dictType ;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDictId() {
        return dictId;
    }

    public void setDictId(String dictId) {
        this.dictId = dictId;
    }

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dict)) return false;
        Dict dict = (Dict) o;
        return Objects.equals(id, dict.id) &&
                Objects.equals(name, dict.name) &&
                Objects.equals(dictId, dict.dictId) &&
                Objects.equals(dictType, dict.dictType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dictId, dictType);
    }

    @Override
    public String toString() {
        return "Dict{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dictId='" + dictId + '\'' +
                ", dictType='" + dictType + '\'' +
                '}';
    }
}
