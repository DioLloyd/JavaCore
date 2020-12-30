package ru.diolloyd.javacore.lesson6;

import com.google.gson.annotations.SerializedName;

public class Dataset {
    @SerializedName("geo_object")
    private GeoObject geoObject;
    private Fact fact;

    public Fact getFact() {
        return fact;
    }

    public GeoObject getGeoObject() {
        return geoObject;
    }

    public class Fact {
        private int temp;                     //текущая
        @SerializedName("feels_like")
        private int feelsLike;               //по ощущениям

        public int getTemp() {
            return temp;
        }

        public int getFeelsLike() {
            return feelsLike;
        }
    }

    public class GeoObject {
        private District district;      //район

        public District getDistrict() {
            return district;
        }

        public Locality getLocality() {
            return locality;
        }

        public class District {
            private String name;

            public String getName() {
                return name;
            }
        }

        private Locality locality;            //город

        public class Locality {
            private String name;

            public String getName() {
                return name;
            }
        }
    }
}

