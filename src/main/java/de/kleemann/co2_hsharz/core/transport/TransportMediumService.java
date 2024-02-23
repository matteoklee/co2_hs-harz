package de.kleemann.co2_hsharz.core.transport;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import de.kleemann.co2_hsharz.api.transport.dto.TransportMediumDTO;
import de.kleemann.co2_hsharz.core.exceptions.CustomEntityNotFoundException;
import de.kleemann.co2_hsharz.core.exceptions.CustomIllegalArgumentException;
import de.kleemann.co2_hsharz.persistence.transport.TransportMediumPersistenceService;
import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumFuel;
import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumName;
import de.kleemann.co2_hsharz.persistence.transport.enums.TransportMediumSize;
import jakarta.annotation.Nullable;
import lombok.NonNull;

/**
 * This Service provides core layer functionality to create, read, update and delete {@link TransportMedium}s
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 09.11.2023
 */
@Service
public class TransportMediumService {

	/**
	 * {@link TransportMediumPersistenceService}
	 */
    private final TransportMediumPersistenceService transportMediumPersistenceService;

    /**
     * Constructs a {@link TransportMediumService} using a {@link TransportMediumPersistenceService}
     * @param transportMediumPersistenceService - {@link TransportMediumPersistenceService}
     */
    public TransportMediumService(TransportMediumPersistenceService transportMediumPersistenceService) {
        this.transportMediumPersistenceService = transportMediumPersistenceService;
    }

    /**
     * Attempts to find a TransportMediumById
     * @param id - {@code long} Id
     * @return {@link TransportMediumImpl} currently null?
     */
    @Nullable
    @Deprecated
    public TransportMediumImpl findTransportMediumById(long id) {
        return null;
    }

    /**
     * Attempts to find a single {@link TransportMedium} by its name
     * @param transportMediumName - {@link TransportMediumName} name
     * @return {@link TransportMediumImpl} if it exists for this name
     * @throws CustomEntityNotFoundException if it does not exist
     */
    //return TransportMedium interface? --> requires mapper?
    public TransportMediumImpl findTransportMediumByName(TransportMediumName transportMediumName) {
        try {
            return new TransportMediumImpl(transportMediumPersistenceService
                    .findTransportMediumByName(transportMediumName));
        } catch (Exception exception) {
            throw new CustomEntityNotFoundException("findTransportMediumByName could not be found.");
        }
    }

    /**
     * Attempts to find a single {@link TransportMedium} by its name and fuel
     * @param transportMediumName - {@link TransportMediumName} name
     * @param transportMediumFuel - {@link TransportMediumFuel} fuel
     * @return {@link TransportMediumImpl} if it exists for this name
     * @throws CustomEntityNotFoundException if it does not exist
     */
    public TransportMediumImpl findTransportMediumByNameAndFuel(TransportMediumName transportMediumName, TransportMediumFuel transportMediumFuel) {
        try {
            return new TransportMediumImpl(transportMediumPersistenceService
                    .findTransportMediumByNameAndFuel(transportMediumName, transportMediumFuel));
        } catch (Exception exception) {
            throw new CustomEntityNotFoundException("findTransportMediumByNameAndFuel could not be found.");
        }
    }

    /**
     * Attempts to find a single {@link TransportMedium} by its name, size and fuel
     * @param transportMediumName - {@link TransportMediumName} name
     * @param transportMediumSize - {@link TransportMediumSize} size
     * @param transportMediumFuel - {@link TransportMediumFuel} fuel
     * @return {@link TransportMediumImpl} if it exists for this name
     * @throws CustomEntityNotFoundException if it does not exist
     */
    public TransportMediumImpl findTransportMediumByNameAndSizeAndFuel(TransportMediumName transportMediumName,
                                                                       TransportMediumSize transportMediumSize,
                                                                       TransportMediumFuel transportMediumFuel) {
        //System.err.println("DEBUG: " + transportMediumName + ", Size: " + transportMediumSize + ", Fuel: " + transportMediumFuel);
        try {
            return new TransportMediumImpl(transportMediumPersistenceService
                    .findTransportMediumByNameAndSizeAndFuel(transportMediumName, transportMediumSize, transportMediumFuel));
        } catch (Exception exception) {
            throw new CustomEntityNotFoundException("findTransportMediumByNameAndSizeAndFuel could not be found.");
        }
    }

    /**
     * Returns a (possibly empty) {@link List} of all saved {@link TransportMedium}s
     * @return {@link List} of {@link TransportMediumImpl}
     */
    public List<TransportMediumImpl> findAllTransportMediums() {
        return transportMediumPersistenceService.findAllTransportMediums()
                .stream()
                .map(TransportMediumImpl::new)
                .collect(Collectors.toList());
    }

    /**
     * Creates a new {@link TransportMedium}
     * @return new {@link TransportMedium}
     */
    public TransportMediumImpl createTransportMedium() {
        return new TransportMediumImpl(transportMediumPersistenceService.createTransportMediumEntity());
    }

    /**
     * Creates a new {@link TransportMedium} with a specific Id
     * @param transportMediumId - {@code long} Id
     * @return new {@link TransportMedium}
     */
    public TransportMediumImpl createTransportMedium(long transportMediumId) {
        return new TransportMediumImpl(transportMediumPersistenceService.createTransportMediumEntity(transportMediumId));
    }

    /**
     * Updates a {@link TransportMedium}
     * @return {@link TransportMediumImpl} Currently null ?
     */
    @Nullable
    @Deprecated
    public TransportMediumImpl updateTransportMedium() {
        return null;
    }

    /**
     * Persists a {@link TransportMedium}
     * @return {@link TransportMediumImpl} Currently null ?
     */
    @Nullable
    @Deprecated
    public TransportMediumImpl persistTransportMedium() {
        return null;
    }

    /**
     * Deletes a {@link TransportMedium}
     * @param transportMedium - {@link TransportMedium}
     * @throws CustomIllegalArgumentException if transportMedium is null
     */
    public void deleteTransportMedium(@NonNull final TransportMediumImpl transportMedium) {
        if(transportMedium == null) {
            throw new CustomIllegalArgumentException("transportMedium must not be null.");
        }
        transportMediumPersistenceService.deleteTransportMedium(transportMedium.getTransportMediumEntity());
    }

    /**
     * Attempts to find a {@link TransportMedium} by custom input from a {@link TransportMediumDTO}
     * @param transportMediumDTO - {@link TransportMediumDTO} custom input
     * @return {@link TransportMediumImpl} if found
     * @throws CustomIllegalArgumentException if <br>
     * <li> {@link TransportMediumDTO#getTransportMediumName()} is null
     * <li> {@link TransportMediumName} could not be found
     * <li> {@link TransportMediumDTO#getTransportMediumSize()} is null and {@link TransportMediumName} is car
     * <li> {@link TransportMediumDTO#getTransportMediumFuel()} is null and {@link TransportMediumName} is car or train or bus
     * <li> {@link TransportMediumSize} does not exist and {@link TransportMediumName} is car
     * <li> {@link TransportMediumFuel} does not exist and {@link TransportMediumName} is car or train or bus
     * <li> no {@link TransportMedium} matching these inputs could be found
     */
    public TransportMediumImpl findTransportMediumByCustomInput(TransportMediumDTO transportMediumDTO) {
        if(transportMediumDTO.getTransportMediumName() == null || transportMediumDTO.getTransportMediumName().isBlank()
                || transportMediumDTO.getTransportMediumName().isEmpty()) {
            throw new CustomIllegalArgumentException("transportMediumName must not be null.");
        }
        //String transportMediumName = transportMediumDTO.getTransportMediumName().toLowerCase();
        TransportMediumName transportMediumName = TransportMediumName.fromName(transportMediumDTO.getTransportMediumName());
        if(transportMediumName == null) {
            throw new CustomIllegalArgumentException("transportMediumName could not be found.");
        }
        String transportMediumSize = "";
        String transportMediumFuel = "";
        switch (transportMediumName) {
            case CAR:
                if(transportMediumDTO.getTransportMediumSize() == null || transportMediumDTO.getTransportMediumSize().isEmpty()
                        || transportMediumDTO.getTransportMediumSize().isBlank()) {
                    throw new CustomIllegalArgumentException("transportMediumSize must not be null for case car.");
                }
                transportMediumSize = transportMediumDTO.getTransportMediumSize();
                if(transportMediumDTO.getTransportMediumFuel() == null || transportMediumDTO.getTransportMediumFuel().isEmpty()
                        || transportMediumDTO.getTransportMediumFuel().isBlank()) {
                    throw new CustomIllegalArgumentException("transportMediumFuel must not be null for case car.");
                }
                transportMediumFuel = transportMediumDTO.getTransportMediumFuel();
                if(TransportMediumSize.fromName(transportMediumSize) == null) {
                    throw new CustomIllegalArgumentException("transportMediumSize does not exists.");
                }
                if(TransportMediumFuel.fromName(transportMediumFuel) == null) {
                    throw new CustomIllegalArgumentException("transportMediumFuel does not exists.");
                }
                return findTransportMediumByNameAndSizeAndFuel(transportMediumName,
                        TransportMediumSize.fromName(transportMediumSize),
                        TransportMediumFuel.fromName(transportMediumFuel));

            case TRAIN:
                if(transportMediumDTO.getTransportMediumFuel() == null || transportMediumDTO.getTransportMediumFuel().isEmpty()
                        || transportMediumDTO.getTransportMediumFuel().isBlank()) {
                    throw new CustomIllegalArgumentException("transportMediumFuel must not be null for case train.");
                }
                transportMediumFuel = transportMediumDTO.getTransportMediumFuel();
                if(TransportMediumFuel.fromName(transportMediumFuel) == null) {
                    throw new CustomIllegalArgumentException("transportMediumFuel does not exists.");
                }
                return findTransportMediumByNameAndFuel(transportMediumName,
                        TransportMediumFuel.fromName(transportMediumFuel));
            case BUS_TOUR, BIKE:
                return findTransportMediumByName(transportMediumName);
            case BUS_PUBLIC:
                if(transportMediumDTO.getTransportMediumFuel() == null || transportMediumDTO.getTransportMediumFuel().isEmpty()
                        || transportMediumDTO.getTransportMediumFuel().isBlank()) {
                    throw new CustomIllegalArgumentException("transportMediumFuel must not be null for case bus_public.");
                }
                transportMediumFuel = transportMediumDTO.getTransportMediumFuel();
                if(TransportMediumFuel.fromName(transportMediumFuel) == null) {
                    throw new CustomIllegalArgumentException("transportMediumFuel does not exists.");
                }
                return findTransportMediumByNameAndFuel(transportMediumName,
                        TransportMediumFuel.fromName(transportMediumFuel));
            case FOOT:
                TransportMediumImpl transportMedium = createTransportMedium();
                transportMedium.setTransportMediumName(TransportMediumName.FOOT);
                transportMedium.setTransportMediumConsumption(0.0);
                return transportMedium;
            default:
                throw new CustomIllegalArgumentException("findTransportMediumByCustomInput could not be found.");
        }
    }


}
