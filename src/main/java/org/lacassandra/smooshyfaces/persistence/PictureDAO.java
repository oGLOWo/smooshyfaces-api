package org.lacassandra.smooshyfaces.persistence;

import org.lacassandra.smooshyfaces.entity.Picture;

import java.util.List;
import java.util.UUID;

public interface PictureDAO extends BaseDAO<Picture, UUID> {
    List<Picture> findByTag(final String tag);
    List<Picture> findByTag(final String tag, final UUID start, int pageSize);
}
