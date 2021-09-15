package pl.course.swimming.competitions.adapter;

import java.time.LocalDateTime;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Supports LocalDateTime in POJO
 *
 * @version 1.0
 * @category Adapter
 */
public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {
    @Override
    public LocalDateTime unmarshal(String s) throws Exception {
        return LocalDateTime.parse(s);
    }
    @Override
    public String marshal(LocalDateTime dateTime) throws Exception {
        return dateTime.toString();
    }   
}

