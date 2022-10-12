package com.vmware.tanzu.tap.accelerator.customerprofile.data;

import com.vmware.tanzu.tap.accelerator.customerprofile.domain.NewCustomerProfile;
import com.vmware.tanzu.tap.accelerator.customerprofile.domain.TestData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class CustomerProfileRepositoryTest {

    @Autowired
    private CustomerProfileRepository subject;

    @Test
	void shouldPersistCustomerProfile() {
		NewCustomerProfile newCustomerProfile = TestData.testNewCustomerProfile();

		var customerProfile = subject.create(newCustomerProfile);
		var actual = subject.findById(customerProfile.id());

		var actualEntity = actual.get();
		assertThat(actualEntity.firstName()).isEqualTo(customerProfile.firstName());
		assertThat(actualEntity.lastName()).isEqualTo(customerProfile.lastName());
		assertThat(actualEntity.email()).isEqualTo(customerProfile.email());
	}
}
