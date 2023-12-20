package de.kleemann.co2_hsharz.core.group;

/**
 * This Interface represents a Group on the core layer <br>
 * A group represents a group of travelers saving their emitted co2 on their journey in our database <br>
 * It has an Id, Nickname, PassPhrase (for authorization) and a Size (number of group members)
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 06.12.2023
 */
public interface Group {

	/**
	 * Getter for the Id of this {@link Group}
	 * @return {@code long} Id of this {@link Group}
	 */
    public long getGroupId();

    /**
	 * Setter for the Id of this {@link Group}
	 * @param groupId - {@code long} New Id of this {@link Group}
	 */
    public void setGroupId(long groupId);

    /**
	 * Getter for the NickName of this {@link Group}
	 * @return {@link String} NickName of this {@link Group}
	 */
    public String getGroupNickName();

    /**
	 * Setter for the NickName of this {@link Group}
	 * @param groupNickName - {@link String} New NickName of this {@link Group}
	 */
    public void setGroupNickName(String groupNickName);

    /**
	 * Getter for the PassPhrase of this {@link Group}
	 * @return {@code String} PassPhrase of this {@link Group}
	 */
    public String getGroupPassPhrase();

    /**
	 * Setter for the PassPhrase of this {@link Group}
	 * @param groupPassPhrase - {@link String} New PassPhrase of this {@link Group}
	 */
    public void setGroupPassPhrase(String groupPassPhrase);

    /**
	 * Getter for the Size of this {@link Group}
	 * @return {@code int} Size of this {@link Group}
	 */
    public int getGroupSize();

    /**
	 * Setter for the Size of this {@link Group}
	 * @param groupSize - {@code int} New Size of this {@link Group}
	 */
    public void setGroupSize(int groupSize);

}
