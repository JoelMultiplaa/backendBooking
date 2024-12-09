package com.example.backendbooking2;

import com.example.backendbooking2.Entity.Category;
import com.example.backendbooking2.Entity.Product;
import com.example.backendbooking2.Repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class BackendBooking2Application {

    public static void main(String[] args) {
        SpringApplication.run(BackendBooking2Application.class, args);
    }

    @Bean
    public CommandLineRunner importData (ProductRepository productRepository){

        return (args) -> {
            List<Product> products = new ArrayList<>();

            //Ambient lys
            products.add(new Product("OEM (Original Equipment Manufacturer) ambient belysning tilbyder en balance mellem stil og funktion, da den ofte kan justeres i farve og intensitet for at tilpasse sig førerens præferencer.", "OEM", "Billedelink", Category.AMBIENT_BELYSNING));
            products.add(new Product("Custom ambient belysning er skræddersyet belysning, der tilføjes eller opgraderes efter bilens oprindelige design for at skabe et unikt og personligt look.", "Custom", "Billedelink", Category.AMBIENT_BELYSNING));

            //Stjernehimmel
            products.add(new Product("Et subtilt, elegant look med få stjerner spredt ud over bilens loft. Belysningen giver en diskret, men stadig imponerende effekt, som minder om en klar nattehimmel.", "500 stjerner", "billed ", Category.STJERNEHIMMEL));
            products.add(new Product("Flere stjerner tilføjes, hvilket skaber en lidt tættere og mere storslået effekt. Himmelrummet bliver mere fyldt, og lyset giver en blød, romantisk atmosfære.", "800 stjerner", "billed", Category.STJERNEHIMMEL));
            products.add(new Product("En endnu mere intens stjernehimmel, hvor stjernerne er tættere på hinanden og dækker et større område af bilens loft. Dette skaber en dramatisk og visuel effekt, der virkelig fremhæver det luksuriøse ved belysningen.", "1000 stjerner", "billed", Category.STJERNEHIMMEL));
            products.add(new Product("Den tætteste og mest imponerende stjernehimmel, hvor stjernerne fylder næsten hele bilens loft. Denne version skaber en spektakulær, nærmest magisk effekt, der giver bilen et ekstraordinært præg og en følelse af at køre under en stjerneklar nattehimmel.", "1300 stjerner", "billed", Category.STJERNEHIMMEL));

            //Blackout
            products.add(new Product("En fleksibel løsning, hvor vinylfolie bruges til at dække kromdetaljer på bilen. Det er hurtigt at påføre, kan fjernes igen, og fås i forskellige finishes som mat, blank eller satin. Ideelt til en midlertidig eller budgetvenlig opgradering.", "Chromedelete Folie","billedet", Category.BLACKOUT));
            products.add(new Product("En permanent løsning, hvor kromdelene slibes og males med autolak. Det giver et eksklusivt og langtidsholdbart resultat med høj modstandsdygtighed mod slid og vejr. Perfekt til dem, der ønsker et fabriksagtigt finish.", "Chromedelete Autolakering ","billedet", Category.BLACKOUT));

            //Kits
            products.add(new Product("Et OEM kit er designet specifikt af bilproducenten til at passe perfekt til bilens originale design. Ideelt til dem, der ønsker at bevare bilens fabriksudseende og pålidelighed.", "OEM Kit ","billedet", Category.KITS));
            products.add(new Product("Et custom kit er skræddersyet eller universelt designet til at tilpasse bilen med et unikt og personligt look.Perfekt til bilentusiaster, der ønsker at skille sig ud.", "Custom kit ","billedet", Category.KITS));

            //Interior
            products.add(new Product("Tilføjelse af opgraderede eller specialdesignede højttalere forbedrer bilens lydkvalitet og skaber en mere intens lytteoplevelse. Kan integreres med ambient belysning for et moderne look.", "Højtaler","billedet", Category.INTERIOR));
            products.add(new Product("Udskiftning eller ombetrækning af rattet giver både forbedret komfort og stil. Muligheder inkluderer læder, alcantara, eller tilføjelse af unikke detaljer som syninger eller karbonfiber.", "Rat","billedet", Category.INTERIOR));
            products.add(new Product("Interiørindpakning med folie giver en hurtig og fleksibel måde at opgradere bilens udseende på. Typiske områder inkluderer instrumentpanel, dørlister og centerkonsol, med finishes som mat, blank, eller metallic look.", "Indpakning","billedet", Category.INTERIOR));

            //Rudetoning
            products.add(new Product("Meget mørk toning, også kaldet limousine-toning. Giver maksimal privatliv og blokering af lys.", "5% Rudetoning","billedet", Category.RUDETONING));
            products.add(new Product("Mørk toning, der stadig tillader lidt lysindtrængning, men skaber et eksklusivt og privat look.", "15% Rudetoning","billedet", Category.RUDETONING));
            products.add(new Product("En populær balance mellem mørk toning og praktisk synlighed, især for bagruder.", "20% Rudetoning","billedet", Category.RUDETONING));
            products.add(new Product("Moderat toning, der giver en sofistikeret stil og reducerer solens varme uden at påvirke synligheden markant.", "30% Rudetoning","billedet", Category.RUDETONING));
            products.add(new Product("Let toning, der giver bilen et moderne look og stadig overholder mange lovgivninger.", "35% Rudetoning","billedet", Category.RUDETONING));
            products.add(new Product("Minimal toning, der giver diskret UV-beskyttelse og en let dæmpning af lysindtrængning.", "50% Rudetoning","billedet", Category.RUDETONING));


            productRepository.saveAll(products);
        };

    }

}
