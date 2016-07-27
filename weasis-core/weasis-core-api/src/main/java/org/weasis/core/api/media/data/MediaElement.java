/*******************************************************************************
 * Copyright (c) 2010 Nicolas Roduit.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Nicolas Roduit - initial API and implementation
 ******************************************************************************/
package org.weasis.core.api.media.data;

import java.io.File;
import java.net.URI;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;

import org.weasis.core.api.util.FileUtil;

public abstract class MediaElement<E> implements Tagable {

    // Metadata of the media
    protected final Map<TagW, Object> tags;
    // Reader of the media (local or remote)
    protected final MediaReader<E> mediaIO;
    // Key to identify the media (the URI passed to the Reader can contain several media elements)
    protected final Object key;

    private volatile boolean loading = false;

    public MediaElement(MediaReader<E> mediaIO, Object key) {
        this.mediaIO = Objects.requireNonNull(mediaIO);
        this.key = key;
        this.tags = Optional.ofNullable(mediaIO.getMediaFragmentTags(key)).orElse(new HashMap<TagW, Object>());
    }

    public MediaReader<E> getMediaReader() {
        return mediaIO;
    }

    @Override
    public void setTag(TagW tag, Object value) {
        if (tag != null) {
            tags.put(tag, value);
        }
    }

    @Override
    public boolean containTagKey(TagW tag) {
        return tags.containsKey(tag);
    }

    @Override
    public Object getTagValue(TagW tag) {
        return tag == null ? null : tags.get(tag);
    }

    public TagW getTagElement(int id) {
        Iterator<TagW> enumVal = tags.keySet().iterator();
        while (enumVal.hasNext()) {
            TagW e = enumVal.next();
            if (e.id == id) {
                return e;
            }
        }
        return null;
    }

    @Override
    public void setTagNoNull(TagW tag, Object value) {
        if (value != null) {
            setTag(tag, value);
        }
    }

    @Override
    public Iterator<Entry<TagW, Object>> getTagEntrySetIterator() {
        return tags.entrySet().iterator();
    }

    public void clearAllTags() {
        tags.clear();
    }

    public abstract void dispose();

    public URI getMediaURI() {
        return mediaIO.getUri();
    }

    /**
     * This file can be the result of a processing like downloading, tiling or uncompressing.
     * 
     * @return the final file that has been load by the reader.
     */
    public File getFile() {
        return mediaIO.getFileCache().getFinalFile();
    }

    public FileCache getFileCache() {
        return mediaIO.getFileCache();
    }

    public String getName() {
        return Paths.get(mediaIO.getUri()).getFileName().toString();
    }

    public Object getKey() {
        return key;
    }

    public boolean saveToFile(File output) {
        if (mediaIO.getFileCache().isElementInMemory()) {
            return mediaIO.buildFile(output);
        }
        return FileUtil.nioCopyFile(mediaIO.getFileCache().getFinalFile(), output);
    }

    public long getLength() {
        return mediaIO.getFileCache().getLength();
    }

    public long getLastModified() {
        return mediaIO.getFileCache().getLastModified();
    }

    public String getMimeType() {
        return mediaIO.getMediaFragmentMimeType();
    }

    protected final synchronized boolean setAsLoading() {
        if (!loading) {
            loading = true;
            return loading;
        }
        return false;
    }

    protected final synchronized void setAsLoaded() {
        loading = false;
    }

    public final synchronized boolean isLoading() {
        return loading;
    }

}
