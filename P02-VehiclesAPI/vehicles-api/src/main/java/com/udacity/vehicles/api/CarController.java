package com.udacity.vehicles.api;


import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import com.udacity.vehicles.client.maps.MapsClient;
import com.udacity.vehicles.client.prices.PriceClient;
import com.udacity.vehicles.domain.car.Car;
import com.udacity.vehicles.service.CarService;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Implements a REST-based controller for the Vehicles API.
 */
@RestController
@RequestMapping("/cars")
@ApiResponses(value = {
        @ApiResponse(code = 400, message = "This is a bad request,please follow the API documentation for the proper format"),
        @ApiResponse(code = 401,message = "Due to Security Constraints, your request can not be Authorized"),
        @ApiResponse(code = 500,message = "Could not Start the server.please make sure the Vehicle microservice is running")
})
class CarController {

    private final CarService carService;
    private final CarResourceAssembler assembler;
    private final PriceClient priceClient;
    private final MapsClient mapsClient;

    CarController(CarService carService, CarResourceAssembler assembler, PriceClient priceClient, MapsClient mapsClient) {
        this.carService = carService;
        this.assembler = assembler;
        this.priceClient = priceClient;
        this.mapsClient = mapsClient;
    }

    /**
     * Creates a list to store any vehicles.
     * @return list of vehicles
     */
    @GetMapping
    Resources<Resource<Car>> list() {
        List<Resource<Car>> resources = carService.list().stream().map(assembler::toResource)
                .collect(Collectors.toList());
        return new Resources<>(resources,
                linkTo(methodOn(CarController.class).list()).withSelfRel());
    }

    /**
     * Gets information of a specific car by ID.
     * @param id the id number of the given vehicle
     * @return all information for the requested vehicle
     */
    @GetMapping("/{id}")
    Resource<Car> get(@PathVariable Long id) {
        /**
         * TODO: Use the `findById` method from the Car Service to get car information.
         * TODO: Use the `assembler` on that car and return the resulting output.
         *   Update the first line as part of the above implementing.
         */
        Car car=carService.findById(id);
        return assembler.toResource(car);
    }

    /**
     * Posts information to create a new vehicle in the system.
     * @param car A new vehicle to add to the system.
     * @return response that the new vehicle was added to the system
     * @throws URISyntaxException if the request contains invalid fields or syntax
     */
    @PostMapping
    ResponseEntity<?> post(@Valid @RequestBody Car car) throws URISyntaxException {
        /**
         * TODO: Use the `save` method from the Car Service to save the input car.
         * TODO: Use the `assembler` on that saved car and return as part of the response.
         *   Update the first line as part of the above implementing.
         */
        Resource<Car> resource = assembler.toResource(carService.save(car));
        return ResponseEntity.created(new URI(resource.getId().expand().getHref())).body(resource);
    }

    /**
     * Updates the information of a vehicle in the system.
     * @param id The ID number for which to update vehicle information.
     * @param car The updated information about the related vehicle.
     * @return response that the vehicle was updated in the system
     */
    @PutMapping("/{id}")
    ResponseEntity<?> put(@PathVariable Long id, @Valid @RequestBody Car car) {
        /**
         * TODO: Set the id of the input car object to the `id` input.
         * TODO: Save the car using the `save` method from the Car service
         * TODO: Use the `assembler` on that updated car and return as part of the response.
         *   Update the first line as part of the above implementing.
         */
        car.setId(id);
        car.setLocation(mapsClient.getAddress(car.getLocation()));
        car.setPrice(priceClient.getPrice(car.getId()));
        Resource<Car> resource = assembler.toResource(carService.save(car));
        return ResponseEntity.ok(resource);
    }

    /**
     * Removes a vehicle from the system.
     * @param id The ID number of the vehicle to remove.
     * @return response that the related vehicle is no longer in the system
     */
    @DeleteMapping("/{id}")
    ResponseEntity<String> delete(@PathVariable Long id) {
        /**
         * TODO: Use the Car Service to delete the requested vehicle.
         */
        carService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
