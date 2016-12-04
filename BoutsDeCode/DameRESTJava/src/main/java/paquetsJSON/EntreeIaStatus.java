package paquetsJSON;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EntreeIaStatus {

        private String token;
        
        @JsonCreator
        public EntreeIaStatus(@JsonProperty("token")String t) {
            token = t;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

}
