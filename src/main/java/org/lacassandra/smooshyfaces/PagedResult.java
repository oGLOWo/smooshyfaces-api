package org.lacassandra.smooshyfaces;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@XmlRootElement (name = "pagedResult")
public class PagedResult<CursorType extends Cursor, DataType> {
    private CursorType cursor;
    private DataType data;

    @XmlElement
    public CursorType getCursor() {
        return cursor;
    }

    public void setCursor(CursorType cursor) {
        this.cursor = cursor;
    }

    @XmlElement
    public DataType getData() {
        return data;
    }

    public void setData(DataType data) {
        this.data = data;
    }
}
