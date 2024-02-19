package de.kleemann.co2_hsharz.persistence.group;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Class "GroupRepository" is used for ...
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 06.12.2023
 */
@Repository
public interface GroupRepository extends JpaRepository<GroupEntity, Long> {

    GroupEntity findByGroupNickNameAndGroupPassPhraseAndGroupSize(String groupNickName, String groupPassPhrase, int groupSize);

}
