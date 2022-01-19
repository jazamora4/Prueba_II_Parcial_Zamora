package ec.edu.espe.arqsoftware.prueba_ii_parcial.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import ec.edu.espe.arqsoftware.prueba_ii_parcial.utilities.ZonedDateTimeReadConverter;
import ec.edu.espe.arqsoftware.prueba_ii_parcial.utilities.ZonedDateTimeWriteConverter;
import org.bson.types.Decimal128;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Date;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Override
    protected String getDatabaseName() {
        return "prueba_ii_parcial";
    }

    @Override
    public MongoClient mongoClient() {
        return MongoClients.create("mongodb+srv://jazamora4:balto1010@cluster0.ursho.mongodb.net/prueba_ii_parcial?retryWrites=true&w=majority");
    }

    @Override
    public boolean autoIndexCreation() {
        return true;
    }

    @Bean
    @Override
    public MappingMongoConverter mappingMongoConverter(MongoDatabaseFactory databaseFactory, MongoCustomConversions customConversions, MongoMappingContext mappingContext) {
        MappingMongoConverter converter = super.mappingMongoConverter(databaseFactory, customConversions, mappingContext);
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
        return converter;
    }

    /**
     * Inject a CustomConversions bean to overwrite the default mapping of BigDecimal.
     *
     * @return a new instance of CustomConversons
     */
    @Bean
    @Override
    public MongoCustomConversions customConversions() {
        Converter<Decimal128, BigDecimal> decimal128ToBigDecimal = new Converter<>() {
            @Override
            public BigDecimal convert(Decimal128 s) {
                return s == null ? null : s.bigDecimalValue();
            }
        };

        Converter<BigDecimal, Decimal128> bigDecimalToDecimal128 = new Converter<>() {
            @Override
            public Decimal128 convert(BigDecimal s) {
                return s == null ? null : new Decimal128(s);
            }
        };

        Converter<Date, ZonedDateTime> zonedDateTimeReadConverter = new ZonedDateTimeReadConverter();
        Converter<ZonedDateTime, Date> zonedDateTimeWriteConverter = new ZonedDateTimeWriteConverter();

        return new MongoCustomConversions(Arrays.asList(decimal128ToBigDecimal, bigDecimalToDecimal128, zonedDateTimeReadConverter, zonedDateTimeWriteConverter));
    }
}
