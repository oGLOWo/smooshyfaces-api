package org.lacassandra.smooshyfaces;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@XmlRootElement(name = "cursor")
public class Cursor<T> {
    public final static String DISCRIMINATOR_VALUE = "C";
    protected String discriminator;
    protected T current;
    protected T next;

    public Cursor(T defaultCurrent, T defaultNext) {
        this.discriminator = DISCRIMINATOR_VALUE;
        this.current = defaultCurrent;
        this.next = defaultNext;
    }

    @XmlElement
    public String getDiscriminator() {
        return discriminator;
    }

    @XmlElement
    public T getCurrent() {
        return current;
    }

    public void setCurrent(T current) {
        this.current = current;
    }

    @XmlElement
    public T getNext() {
        return next;
    }

    public void setNext(T next) {
        this.next = next;
    }

    @XmlElement
    public boolean hasNext() {
        return next != null;
    }
}
