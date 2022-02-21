package ru.preview;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HZConf {

    @Bean
    @ConfigurationProperties("hazelcast.client")
    public HazelCastClientConfig storeCommonsHazelCastClientConfig() {
        return new HazelCastClientConfig();
    }

    @Bean
    public HazelcastInstance getStoreCommonsHazelCastClient() {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.setClusterName(storeCommonsHazelCastClientConfig().getClusterName());
        clientConfig.getNetworkConfig().addAddress(storeCommonsHazelCastClientConfig().getClusterAddress());
        clientConfig.setClassLoader(this.getClass().getClassLoader());

        var clientUserCodeDeploymentConfig = clientConfig.getUserCodeDeploymentConfig();
        clientUserCodeDeploymentConfig.setEnabled(true).addClass(UploadClass.class);

        clientConfig.setUserCodeDeploymentConfig(clientUserCodeDeploymentConfig);
        var client = HazelcastClient.newHazelcastClient(clientConfig);

        return client;
    }

    public static class HazelCastClientConfig {

        private String clusterName;
        private String clusterAddress;

        public void setClusterName(String clusterName) {
            this.clusterName = clusterName;
        }

        public void setClusterAddress(String clusterAddress) {
            this.clusterAddress = clusterAddress;
        }

        public String getClusterName() {
            return clusterName;
        }

        public String getClusterAddress() {
            return clusterAddress;
        }
    }
}

