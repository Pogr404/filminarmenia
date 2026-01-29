CREATE TABLE registration.users
(
    user_id           UUID primary key not null,
    first_name        varchar(64)      not null,
    last_name         varchar(64)      not null,
    email             varchar(64)      not null unique,
    password          varchar(64)      not null,
    role              varchar(12)      not null,
    status            varchar(12)      not null,
    verification_code varchar(15),
    reset_token       varchar(15)

);

CREATE TABLE registration.countries
(
    country_id          UUID primary key not null,
    name                varchar(64) not null
);

CREATE TABLE registration.foodPlaces
(
    food_place_id       UUID primary key not null,
    name                varchar(64) not null
);

CREATE TABLE registration.gasStations
(
    gas_station_id      UUID primary key not null,
    name                varchar(64) not null
);

CREATE TABLE registration.nearbyHotels
(
    nearby_hotels_id    UUID primary key not null,
    name                varchar(64) not null,
    rate                varchar(255) NOT NULL,
    website             varchar(255) NOT NULL
);

CREATE TABLE registration.nearbyHospitals
(
    nearby_hospitals_id UUID primary key not null,
    name                varchar(64) not null
);

CREATE TABLE registration.nearbyPharmacies
(
    nearby_pharmacy_id  UUID primary key not null,
    name                varchar(64) not null
);

CREATE TABLE registration.photos
(
    photo_id            UUID primary key not null,
    name                varchar(64) not null,
    size                varchar(64) not null,
    path                varchar(128) not null
);

CREATE TABLE registration.photo360
(
    photo_id            UUID primary key not null,
    name                varchar(64) not null,
    size                varchar(64) not null,
    path                varchar(128) not null,
    description         varchar(512) not null
);

CREATE TABLE registration.roadQuality
(
    road_quality_id     UUID primary key not null,
    name                varchar(64)
);

CREATE TABLE registration.transportType
(
    transport_type_id   UUID primary key not null,
    name                varchar(64) not null
);

CREATE TABLE registration.climateZones
(
    climate_zone_id     UUID primary key not null,
    name                varchar(64) not null
);

CREATE TABLE registration.placeType
(
    place_type_id       UUID primary key not null,
    name                varchar(64) not null
);

CREATE TABLE registration.place
(
    id                      UUID PRIMARY KEY NOT NULL,
    name                    VARCHAR(64) NOT NULL,
    coordinates             VARCHAR(64) NOT NULL,
    place_type_id           UUID NOT NULL,
    country_id              UUID NOT NULL,
    climate_zone_id         UUID NOT NULL,
    photo_360_id            UUID NOT NULL,
    description             VARCHAR(512) NOT NULL,
    road_quality_id         UUID NOT NULL,
    recommended             BOOLEAN NOT NULL,

    nearby_hotels_id        UUID NOT NULL,
    nearby_hospitals_id     UUID NOT NULL,
    nearby_pharmacies_id    UUID NOT NULL,
    gas_stations_id         UUID NOT NULL,
    food_places_id          UUID NOT NULL,
    photos_id               UUID NOT NULL,
    transport_type_id       UUID NOT NULL,
    keyword_id              UUID,
    distance_from_hotel     numeric NOT NULL,
    distance_from_hospital  numeric NOT NULL,
    distance_from_pharmacy  numeric NOT NULL,
    distance_from_gas_station   numeric NOT NULL,
    distance_from_food_place    numeric NOT NULL

);


CREATE TABLE registration.hotels_to_places
(
    place_id            UUID not null,
    nearby_hotels_id    UUID not null
);

CREATE TABLE registration.hospitals_to_places
(
    place_id            UUID not null,
    nearby_hospitals_id UUID not null
);

CREATE TABLE registration.pharmacies_to_places
(
    place_id            UUID not null,
    nearby_pharmacies_id UUID not null
);

CREATE TABLE registration.gasStations_to_places
(
    place_id            UUID not null,
    gas_station_id      UUID not null
);

CREATE TABLE registration.foodPlaces_to_places
(
    place_id            UUID not null,
    food_place_id       UUID not null
);

CREATE TABLE registration.transportType_to_places
(
    place_id            UUID not null,
    transport_type_id   UUID not null
);

CREATE TABLE registration.keywords
(
    keyword_id          UUID primary key not null,
    name                varchar(16)
);

CREATE TABLE registration.keyword_to_places
(
    id                  UUID primary key not null,
    place_id            UUID not null,
    keyword_id          UUID not null
)

CREATE TABLE registration.placeKeyword
(
    id                  UUID primary key not null,
    place_id            UUID not null,
    keyword_id          UUID not null
)
