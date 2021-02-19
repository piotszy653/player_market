package pl.piotrszymanski.player_market.config.auditing;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaAuditing(dateTimeProviderRef = "offsetDateTimeProvider")
public class JpaCoreAuditingConfiguration {
}
