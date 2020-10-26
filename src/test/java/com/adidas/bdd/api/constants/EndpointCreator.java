package com.adidas.bdd.api.constants;

public class EndpointCreator {

    public static String GET_AVAILABLE_PETS = "v2/pet/findByStatus";
    public static String PET_STORE_ENDPOINT = "v2/pet";
//    public static String PET_STORE_ENDPOINT = "v2/pet";

    private static String join(String... elements) {
        return String.join("/", elements);
    }

    public static String getPetDetailsById(String petId){
        return join(PET_STORE_ENDPOINT, petId);
    }

    public static String deletePetDetails(String petId){
        return join(PET_STORE_ENDPOINT, petId);
    }



}
