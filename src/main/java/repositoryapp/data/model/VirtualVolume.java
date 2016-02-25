package repositoryapp.data.model;

import repositoryapp.data.model.requests.VPLEXRESTAttributes;

public class VirtualVolume implements BaseDBObject
{

    private String name;
    private String parent;
    private String blockSize;
    private String blockCount;
    private String capacity;
    private String consistencyGroup;
    private String locality;
    public String getBlockCount()
    {
        return blockCount;
    }


    public void setBlockCount(String blockCount)
    {
        this.blockCount = blockCount;
    }


    public String getLocality()
    {
        return locality;
    }


    public void setLocality(String locality)
    {
        this.locality = locality;
    }


    private String _id;
    private String provisionType;
    
    
    public String getProvisionType()
    {
        return provisionType;
    }


    public void setProvisionType(String provisionType)
    {
        this.provisionType = provisionType;
    }


    public VirtualVolume(VPLEXRESTAttributes[] attributes, String vvParent)
    {
        setParent(vvParent);
        processVirtualVolumeAttributes(attributes);
        
    }
    
    
    public void processVirtualVolumeAttributes(VPLEXRESTAttributes[] attributes)
    {
        for(int i = 0; i < attributes.length;i++)
        {
            String name = attributes[i].name;
            Object value = attributes[i].value;
            if(value instanceof String)
            {
                String stringVal = (String)value;
                setVVAttribute(name,stringVal);  
            }
            else
            {
                //System.out.print("."); 
                //Log something
            }
             
        }
    }
    
    
    private void setVVAttribute(String name, String value)
    {
        if(name.equalsIgnoreCase("name"))
        {
            setName(value.toString());
        }
        else if(name.equalsIgnoreCase("capacity"))
        {
            setCapacity(value.toString());
        }
        else if(name.equalsIgnoreCase("parent"))
        {
            setParent(value.toString());
        }
        else if(name.equalsIgnoreCase("provision-type"))
        {
            setProvisionType(value);
        }
        else if(name.equalsIgnoreCase("consistency-group"))
        {
            setConsistencyGroup(value);
        }
        else if(name.equalsIgnoreCase("block-size"))
        {
            setBlockSize(value);
        }
        else if(name.equalsIgnoreCase("locality"))
        {
            setLocality(value);
        }
        else if(name.equalsIgnoreCase("block-count"))
        {
            setBlockCount(value);
        }
    }
    
    public String get_id()
    {
        return _id;
    }


    public void set_id(String _id)
    {
        this._id = _id;
    }


    public String getName()
    {
        return name;
    }


    public void setName(String name)
    {
        this.name = name;
        set_id(getParent()+"/"+name);
    }


    public String getParent()
    {
        return parent;
    }


    public void setParent(String parent)
    {
        this.parent = parent;
    }


    public String getBlockSize()
    {
        return blockSize;
    }


    public void setBlockSize(String blockSize)
    {
        this.blockSize = blockSize;
    }


    public String getCapacity()
    {
        return capacity;
    }


    public void setCapacity(String capacity)
    {
        this.capacity = capacity;
    }


    public String getConsistencyGroup()
    {
        return consistencyGroup;
    }


    public void setConsistencyGroup(String consistencyGroup)
    {
        this.consistencyGroup = consistencyGroup;
    }

   
    
}
