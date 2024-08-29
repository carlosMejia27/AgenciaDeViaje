package com.example.demo;

import com.example.demo.dominan.repository.mongo.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Travel  {
// implements CommandLineRunner
//	private final HotelRepository hotelRepository;
//	private final FlyRepository  flyRepository;
//	private final TicketRepository ticketRepository;
//	private final ReservacionRepository reservacionRepository;
//	private final TourRepository tourRepository;
//	private final CustomerRepository customerRepository;
//
//	public Travel(HotelRepository hotelRepository, FlyRepository flyRepository,
//				  TicketRepository ticketRepository, ReservacionRepository reservacionRepository,
//				  TourRepository tourRepository, CustomerRepository customerRepository) {
//		this.hotelRepository = hotelRepository;
//		this.flyRepository = flyRepository;
//		this.ticketRepository = ticketRepository;
//		this.reservacionRepository = reservacionRepository;
//		this.tourRepository = tourRepository;
//		this.customerRepository = customerRepository;
//	}

//	@Autowired
//	private AppUserRepository appUserRepository;
	public static void main(String[] args) {
		SpringApplication.run(Travel.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		//this.appUserRepository.findAll().forEach(System.out::println);
//		System.out.println(this.appUserRepository.findByUsername("misterX").orElseThrow());
//
//	}


	// var fly=flyRepository.findById(15L).get();
	// var hotel=hotelRepository.findById(7L).get();
    // var ticket=ticketRepository.findById(UUID.fromString("32345678-1234-5678-4234-567812345678")).get();
	// var reservacion=reservacionRepository.findById(UUID.fromString("12345678-1234-5678-1234-567812345678")).get();
	// var customer=customerRepository.findById("BBMB771012HMCRR022").get();

//	    log.info(String.valueOf(fly));
//		log.info(String.valueOf(hotel));
//		log.info(String.valueOf(ticket));
	//	log.info(String.valueOf(reservacion));
		//log.info(String.valueOf(customer));
		//this.flyRepository.selectLessPrice(BigDecimal.valueOf(20)).forEach(System.out::println);
//		this.flyRepository.selectLessprice(BigDecimal.valueOf(20)).forEach(f-> System.out.println(f));
        //this.flyRepository.selectbetweenprice(BigDecimal.valueOf(10),BigDecimal.valueOf(15)).forEach(System.out::println);
//this.flyRepository.selectOriginDestino("Grecia" ,"Mexico").forEach(System.out::println);

		//fly.getTickets().forEach(System.out::println);
//		hotelRepository.findByPriceLessThan(BigDecimal.valueOf(100)).forEach(System.out::println);
       // hotelRepository.findByPriceIsBetween(BigDecimal.valueOf(10),BigDecimal.valueOf(15)).forEach(System.out::println);
//hotelRepository.findByRatingGreaterThan(3).forEach(System.out::println);
//		var clinete=customerRepository.findById("VIKI771012HMCRG093").orElseThrow();
//		log.info("*****************"+clinete.getFullName());
//
//
//
//		var fly=flyRepository.findById(11L).orElseThrow();
//		log.info("Fly : "+fly.getOriginName()+"  **** "+ fly.getDestinyName());
//
//		var hotel=hotelRepository.findById(3L).orElseThrow();
//
//		log.info("hotel : "+hotel.getName());
//
//		var tour=Tour.builder()
//				.customer(clinete).build();
//
//		var tiket= Ticket.builder()
//				.id(UUID.randomUUID())
//				.departureDate(LocalDateTime.now())
//				.purchaseDate(LocalDateTime.now())
//				.arrivalDate(LocalDateTime.now())
//				.price(fly.getPrice().multiply(BigDecimal.TEN))
//				.fly(fly)
//				.tour(tour)
//				.customer(clinete)
//				.build();
//
//		var reservacion= Reservation.builder()
//				.id(UUID.randomUUID())
//				.dateReservation(LocalDateTime.now())
//				.dateEnd((LocalDate.now()).plusDays(2))
//				.dateStart((LocalDate.now()).plusDays(1))
//				.hotel(hotel)
//				.customer(clinete)
//				.tour(tour)
//				.totalDays(1)
//				.price(hotel.getPrice().multiply(BigDecimal.TEN))
//				.build();
//
//		tour.addReservation(reservacion);
//		tour.updateReservation();
//
//		tour.addTicket(tiket);
//		tour.updateTicket();
//
//      var borrar =this.tourRepository.save(tour);
//
//	  Thread.sleep(8000);
//	  this.tourRepository.deleteById(borrar.getId());

}
