package org.lacassandra.smooshyfaces;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@XmlRootElement(name = "cursor")
public class DateRangeCursor extends Cursor<Long> {
    public static final String DISCRIMINATOR_VALUE = "DRC";
    protected Date startDate;
    protected Date endDate;

    public DateRangeCursor() {
        super(0L, 0L);
        this.discriminator = DISCRIMINATOR_VALUE;
    }

    @XmlElement
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @XmlElement
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
