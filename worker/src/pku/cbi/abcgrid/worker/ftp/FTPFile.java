/**
 *
 *  edtFTPj
 * 
 *  Copyright (C) 2000-2004 Enterprise Distributed Technologies Ltd
 *
 *  www.enterprisedt.com
 *
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public
 *  License as published by the Free Software Foundation; either
 *  version 2.1 of the License, or (at your option) any later version.
 *
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this library; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 *  Bug fixes, suggestions and comments should be sent to bruce@enterprisedt.com
 *
 *  Change Log:
 *
 *    $Log: FTPFile.java,v $
 *    Revision 1.1.1.1  2004/11/21 01:54:37  suny
 *     gridc alpha version
 *
 *    Revision 1.4  2004/05/20 19:47:00  bruceb
 *    blanks in names fix
 *
 *    Revision 1.3  2004/05/01 11:44:21  bruceb
 *    modified for server returning "total 3943" as first line
 *
 *    Revision 1.2  2004/04/17 23:42:07  bruceb
 *    file parsing part II
 *
 *    Revision 1.1  2004/04/17 18:37:23  bruceb
 *    new parse functionality
 *
 */

package pku.cbi.abcgrid.worker.ftp;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *  Represents a remote file (implementation)
 *
 *  @author      Bruce Blackshaw
 *  @version     $Revision: 1.1.1.1 $
 */
public class FTPFile {
    
    /**
     *  Revision control id
     */
    protected static String cvsId = "@(#)$Id: FTPFile.java,v 1.1.1.1 2004/11/21 01:54:37 suny Exp $";
    
    /**
     * Unknown remote server type
     */
    public final static int UNKNOWN = -1;  
    
    /**
     * Windows type
     */
    public final static int WINDOWS = 0;
    
    /**
     * UNIX type
     */
    public final static int UNIX = 1;
        
    /**
     * Date formatter type 1
     */
    private final static SimpleDateFormat formatter =
        new SimpleDateFormat("dd-MM-yyyy HH:mm");
    
    /**
     * Type of file
     */
    private int type;
    
    /**
     * Is this file a symbolic link?
     */
    protected boolean isLink = false;
    
    /**
     * Permission bits string
     */
    protected String permissions;
       
    /**
     * Is this a directory?
     */
    protected boolean isDir = false;
    
    /**
     * Size of file
     */
    protected long size = 0L;
    
    /**
     * File/dir name
     */
    protected String name;
    
    /**
     * Name of file this is linked to
     */
    protected String linkedname;
    
    /**
     * Owner if known
     */
    protected String owner;
    
    /**
     * Group if known
     */
    protected String group;
    
    /**
     * Last modified
     */
    protected Date lastModified;
    
    /**
     * Raw string
     */
    protected String raw;
    
    /**
     * Constructor
     * 
     * @param type          type of file
     * @param raw           raw string returned from server
     * @param name          name of file
     * @param size          size of file
     * @param isDir         true if a directory
     * @param lastModified  last modified timestamp
     */
    FTPFile(int type, String raw, String name, long size, boolean isDir, Date lastModified) {
        this.type = type;
        this.raw = raw;
        this.name = name;
        this.size = size;
        this.isDir = isDir;
        this.lastModified = lastModified;
    }
    
    /**
     * Get the type of file, i.e UNIX
     * 
     * @return the integer type of the file
     */
    public int getType() {
        return type;
    }
    
    /**
     * @return Returns the group.
     */
    public String getGroup() {
        return group;
    }

    /**
     * @return Returns the isDir.
     */
    public boolean isDir() {
        return isDir;
    }

    /**
     * @return Returns the lastModified date.
     */
    public Date lastModified() {
        return lastModified;
    }

    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }

    /**
     * @return Returns the owner.
     */
    public String getOwner() {
        return owner;
    }

    /**
     * @return Returns the raw server string.
     */
    public String getRaw() {
        return raw;
    }

    /**
     * @return Returns the size.
     */
    public long size() {
        return size;
    }
    
    /**
     * @return Returns the permissions.
     */
    public String getPermissions() {
        return permissions;
    }
    
    /**
     * @return Returns true if file is a symlink
     */
    public boolean isLink() {
        return isLink;
    }
    
    /**
     * @return Returns the linkedname.
     */
    public String getLinkedname() {
        return linkedname;
    }
    
    /**
     * @return string representation
     */
    public String toString() {
        StringBuffer buf = new StringBuffer(raw);
        buf.append("[").
            append("Name=").append(name).append(",").
            append("Size=").append(size).append(",").
            append("Permissions=").append(permissions).append(",").
            append("Owner=").append(owner).append(",").
            append("Group=").append(group).append(",").
            append("Is link=").append(isLink).append(",").
            append("Is dir=").append(isDir).append(",").
            append("Linked name=").append(linkedname).append(",").
            append("Permissions=").append(permissions).append(",").
            append("Last modified=").append(formatter.format(lastModified)).append("]");
        return buf.toString();
    }
    

    /**
     * @param group The group to set.
     */
    void setGroup(String group) {
        this.group = group;
    }

    /**
     * @param isDir The isDir to set.
     */
    void setDir(boolean isDir) {
        this.isDir = isDir;
    }

    /**
     * @param isLink The isLink to set.
     */
    void setLink(boolean isLink) {
        this.isLink = isLink;
    }
    
    /**
     * @param linkedname The linked name to set.
     */
    void setLinkedName(String linkedname) {
        this.linkedname = linkedname;
    }

    /**
     * @param owner The owner to set.
     */
    void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * @param permissions The permissions to set.
     */
    void setPermissions(String permissions) {
        this.permissions = permissions;
    }

}
