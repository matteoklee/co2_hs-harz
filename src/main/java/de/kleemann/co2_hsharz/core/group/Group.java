package de.kleemann.co2_hsharz.core.group;

/**
 * Class "Group" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 06.12.2023
 */
public interface Group {

    public long getGroupId();

    public void setGroupId(long groupId);

    public String getGroupNickName();

    public void setGroupNickName(String groupNickName);

    public String getGroupPassPhrase();

    public void setGroupPassPhrase(String groupPassPhrase);

    public int getGroupSize();

    public void setGroupSize(int groupSize);

}
