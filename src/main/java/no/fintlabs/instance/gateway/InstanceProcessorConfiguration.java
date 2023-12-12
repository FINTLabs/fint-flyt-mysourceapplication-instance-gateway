package no.fintlabs.instance.gateway;

import no.fintlabs.gateway.instance.InstanceProcessor;
import no.fintlabs.gateway.instance.InstanceProcessorFactoryService;
import no.fintlabs.instance.gateway.collectionandfiles.IncomingInstanceWithCollectionOfFiles;
import no.fintlabs.instance.gateway.collectionandfiles.IncomingInstanceWithCollectionOfFilesMappingService;
import no.fintlabs.instance.gateway.simple.IncomingInstance;
import no.fintlabs.instance.gateway.simple.IncomingInstanceMappingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Configuration
public class InstanceProcessorConfiguration {

    @Bean
    public InstanceProcessor<IncomingInstance> simpleInstanceProcessor(
            InstanceProcessorFactoryService instanceProcessorFactoryService,
            IncomingInstanceMappingService incomingInstanceMappingService
    ) {
        return instanceProcessorFactoryService.createInstanceProcessor(
                "instance",
                incomingInstance -> Optional.of(incomingInstance.getStringValue1()),
                incomingInstanceMappingService
        );
    }

    @Bean
    public InstanceProcessor<IncomingInstanceWithCollectionOfFiles> instanceWithCollectionAndFilesProcessor(
            InstanceProcessorFactoryService instanceProcessorFactoryService,
            IncomingInstanceWithCollectionOfFilesMappingService incomingInstanceWithCollectionOfFilesMappingService
    ) {
        return instanceProcessorFactoryService.createInstanceProcessor(
                "instanceWithCollectionAndFiles",
                incomingInstanceWithCollectionAndFiles -> Optional.ofNullable(
                        incomingInstanceWithCollectionAndFiles.getStringValue2()
                ),
                incomingInstanceWithCollectionOfFilesMappingService
        );
    }


}
