/*
 * This file is part of the HyperGraphDB source distribution. This is copyrighted
 * software. For permitted uses, licensing options and redistribution, please see 
 * the LicensingInformation file at the root level of the distribution. 
 *
 * Copyright (c) 2005-2006
 *  Kobrix Software, Inc.  All rights reserved.
 */
/**
 * 
 */
package org.hypergraphdb.storage;

import org.hypergraphdb.HGException;

import com.sleepycat.db.Cursor;
import com.sleepycat.db.DatabaseEntry;
import com.sleepycat.db.LockMode;
import com.sleepycat.db.OperationStatus;

class KeyRangeForwardResultSet extends IndexResultSet
{    
    protected Object advance()
    {
        try
        {
            OperationStatus status = cursor.getNext(key, data, LockMode.DEFAULT);
            if (status == OperationStatus.SUCCESS)
            	return converter.fromByteArray(data.getData());
            else
                return null;
        }
        catch (Throwable t)
        {
            closeNoException();
            throw new HGException(t);
        }            
    }
    
    protected Object back()
    {
        try
        {
            OperationStatus status = cursor.getPrev(key, data, LockMode.DEFAULT);
            if (status == OperationStatus.SUCCESS)
                return converter.fromByteArray(data.getData());
            else
                return null;
        }
        catch (Throwable t)
        {
            closeNoException();
            throw new HGException(t);
        }                        
    }
    
    public KeyRangeForwardResultSet()
    {            
    }
    
    public KeyRangeForwardResultSet(Cursor cursor, DatabaseEntry key, ByteArrayConverter converter)
    {
        super(cursor, key, converter);         
    }        
    
    public boolean isOrdered()
    {
    	return true;
    }
}