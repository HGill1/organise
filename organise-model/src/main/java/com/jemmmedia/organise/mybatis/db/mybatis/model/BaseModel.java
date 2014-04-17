package com.jemmmedia.organise.mybatis.db.mybatis.model;

import java.io.Serializable;
import java.util.Date;


public class BaseModel implements Serializable
{
    /**
	 * 
	 */
	//private static final long serialVersionUID = 7736556436994351738L;

	/**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Long id;

    private Date createdAt;

    private Date updatedAt;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Date getCreatedAt()
    {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt)
    {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt()
    {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt)
    {
        this.updatedAt = updatedAt;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BaseModel other = (BaseModel) obj;
        if (id == null)
        {
            if (other.id != null)
                return false;
        }
        else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "BaseModel [" + (id != null ? "id=" + id : "") + "]";
    }

}
