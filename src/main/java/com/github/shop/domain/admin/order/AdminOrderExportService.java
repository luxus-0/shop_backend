package com.github.shop.domain.admin.order;

import com.github.shop.domain.address.Address;
import com.github.shop.domain.constant.Constants;
import com.github.shop.domain.contact.Contact;
import com.github.shop.domain.customer.Customer;
import com.github.shop.domain.order.OrderStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class AdminOrderExportService {
    public static final CSVFormat CSV_FORMAT = CSVFormat.DEFAULT.builder()
            .setHeader(Constants.HEADERS)
            .build();
    private final AdminOrderRepository adminOrderRepository;

    private static void printOrder(List<AdminOrder> adminOrders, CSVPrinter printer) throws IOException {
        for (AdminOrder adminOrder : adminOrders) {
            printer.printRecord(
                    adminOrder.getId(),
                    getCustomer(adminOrder).getFirstName(),
                    getCustomer(adminOrder).getLastName(),
                    getCustomer(adminOrder).getPesel(),
                    getAddress(adminOrder).getCity(),
                    getAddress(adminOrder).getStreet(),
                    getAddress(adminOrder).getZipCode(),
                    getContact(adminOrder).getEmail(),
                    getContact(adminOrder).getPhone(),
                    adminOrder.getPlaceDate(),
                    adminOrder.getOrderStatus(),
                    adminOrder.getGrossValue(),
                    adminOrder.getPayment());

        }
    }

    private static Contact getContact(AdminOrder adminOrder) {
        return adminOrder.getCustomers().stream().map(Customer::getContacts).flatMap(Collection::stream).findAny().orElseThrow();
    }

    private static Address getAddress(AdminOrder adminOrder) {
        return getCustomer(adminOrder).getAddresses().stream().findAny().orElseThrow();
    }

    private static Customer getCustomer(AdminOrder adminOrder) {
        return adminOrder.getCustomers().stream().findAny().orElseThrow();
    }

    public List<AdminOrder> exportOrders(LocalDateTime from, LocalDateTime to, OrderStatus orderStatus) {
        return adminOrderRepository.findAllByPlaceDateBetweenAndOrderStatus(from, to, orderStatus);
    }

    public ByteArrayInputStream exportOrdersToCSV(List<AdminOrder> adminOrders) {
        try (ByteArrayOutputStream stream = new ByteArrayOutputStream();
             CSVPrinter printer = new CSVPrinter(new PrintWriter(stream), CSV_FORMAT)) {
            printOrder(adminOrders, printer);
            printer.flush();
            return new ByteArrayInputStream(stream.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("An error occurred while exporting orders to CSV" + e.getMessage());
        }
    }
}
