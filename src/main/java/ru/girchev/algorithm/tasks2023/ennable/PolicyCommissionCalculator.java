package ru.girchev.algorithm.tasks2023.ennable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import ru.girchev.algorithm.core.Condition;
import ru.girchev.algorithm.core.SolutionMethod;
import ru.girchev.algorithm.core.Utils;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ru.girchev.algorithm.core.Executor.start;
import static ru.girchev.algorithm.core.Utils.convertToDate;

/**
 * You need to create a method that would calculate the main department for each specialist.
 * The main department is the one in which the specialist has the most overall commission on
 * active policies.
 * <p>
 * The specialist’s commission is calculated as a percentage of the total policy commission.
 * <p>
 * A policy is considered active if it has already started but has not yet finished
 *
 * @author ngirchev@gmail.com on 15.12.2023.
 */
public abstract class PolicyCommissionCalculator {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    protected static class Policy {
        Integer policyId;
        Date startDate;
        Date endDate;
        /**
         * Total policy commission in cents.
         */
        Long commission;
        /**
         * Policy Department ID
         */
        Integer departmentId;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    protected static class PolicySpecialist {
        Integer specialistId;
        Integer policyId;
        /**
         * The percentage of commission that a specialist receives from the total commission of the policy.
         * Possible values from 0 to 1
         */
        Double commissionPct;
    }

    @SneakyThrows
    public static void main(String[] args) {
        start(Utils.getExecutionType(args), PolicyCommissionCalculator.class,
                createSimpleCondition(),
                createBigDataCondition()
        );
    }

    private static Condition createSimpleCondition() {
        LocalDate now = LocalDate.now();
        List<Policy> policies = List.of(
                new Policy(1, convertToDate(now.minusDays(1)), convertToDate(now.plusDays(1)), 10000L, 1),
                new Policy(2, convertToDate(now.minusDays(1)), convertToDate(now.plusDays(1)), 10000L, 1),
                new Policy(3, convertToDate(now.minusDays(15)), convertToDate(now.minusDays(13)), 10000L, 1),
                new Policy(4, convertToDate(now.minusDays(1)), convertToDate(now.plusDays(1)), 10000L, 1),
                new Policy(5, convertToDate(now.minusDays(1)), convertToDate(now.plusDays(1)), 1000000L, 2)
        );
        List<PolicySpecialist> policySpecialists = List.of(
                new PolicySpecialist(1, 1, 0.10),
                new PolicySpecialist(2, 2, 0.10),
                new PolicySpecialist(1, 4, 0.10),
                new PolicySpecialist(1, 5, 0.10),
                new PolicySpecialist(1, 3, 0.10)
        );
        HashMap<Integer, Integer> result = new HashMap<>();
        result.put(1, 2);
        result.put(2, 1);
        return new Condition(result, policies, policySpecialists);
    }

    private static final Random random = new Random();

    private static Condition createBigDataCondition() {
        LocalDate now = LocalDate.now();
        AtomicInteger policyIdCounter = new AtomicInteger(1);
        AtomicInteger departmentCounter = new AtomicInteger(1);
        List<Policy> policies = Stream.generate(() -> {
            int policyId = policyIdCounter.getAndIncrement();
            int departmentId = departmentCounter.getAndIncrement();
            if (departmentId > 100) {
                departmentCounter.set(1);
            }
            return new Policy(
                    policyId,
                    convertToDate(now.minusDays(random.nextInt(1000) + 1)),
                    convertToDate(now.plusDays(random.nextInt(1000) + 1)),
                    1000L,
                    departmentId
            );
        }).limit(1_000_000).toList();
        AtomicInteger specialistCounter = new AtomicInteger(1);
        List<PolicySpecialist> policySpecialists = policies.stream().flatMap(p -> {
            List<PolicySpecialist> psTmp = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                int specialistId = specialistCounter.getAndIncrement();
                if (specialistId > 1000) {
                    specialistCounter.set(1);
                }
                psTmp.add(
                        new PolicySpecialist(
                                specialistId,
                                p.policyId,
                                switch (i) {
                                    case 0 -> 0.1;
                                    case 1 -> 0.2;
                                    case 2 -> 0.08;
                                    default -> throw new IllegalArgumentException("Unexpected value: " + i);
                                }
                        )
                );
            }

            return psTmp.stream();
        }).toList();

        var str = "1=1, 2=1, 3=1, 4=1, 5=1, 6=1, 7=2, 8=2, 9=2, 10=1, 11=1, 12=3, 13=4, 14=2, 15=1, 16=1, 17=1, 18=1, 19=1, 20=1, 21=1, 22=1, 23=2, 24=2, 25=2, 26=1, 27=1, 28=1, 29=1, 30=1, 31=1, 32=1, 33=1, 34=2, 35=2, 36=2, 37=1, 38=1, 39=3, 40=2, 41=2, 42=1, 43=1, 44=1, 45=1, 46=1, 47=1, 48=1, 49=1, 50=2, 51=2, 52=2, 53=1, 54=1, 55=1, 56=1, 57=1, 58=1, 59=1, 60=1, 61=2, 62=2, 63=2, 64=1, 65=1, 66=3, 67=2, 68=2, 69=1, 70=1, 71=1, 72=1, 73=1, 74=1, 75=1, 76=1, 77=2, 78=2, 79=2, 80=1, 81=1, 82=1, 83=1, 84=1, 85=1, 86=1, 87=1, 88=2, 89=2, 90=2, 91=1, 92=1, 93=3, 94=2, 95=2, 96=1, 97=1, 98=1, 99=1, 100=1, 101=1, 102=1, 103=1, 104=2, 105=2, 106=2, 107=1, 108=1, 109=1, 110=1, 111=1, 112=1, 113=1, 114=1, 115=2, 116=3, 117=3, 118=1, 119=1, 120=1, 121=1, 122=1, 123=1, 124=1, 125=1, 126=2, 127=2, 128=2, 129=1, 130=1, 131=3, 132=2, 133=2, 134=1, 135=1, 136=1, 137=1, 138=1, 139=1, 140=1, 141=1, 142=2, 143=2, 144=2, 145=1, 146=1, 147=1, 148=1, 149=1, 150=1, 151=1, 152=1, 153=2, 154=2, 155=2, 156=1, 157=1, 158=4, 159=2, 160=2, 161=1, 162=1, 163=1, 164=1, 165=1, 166=1, 167=1, 168=1, 169=2, 170=2, 171=2, 172=1, 173=1, 174=1, 175=1, 176=1, 177=1, 178=1, 179=1, 180=2, 181=2, 182=2, 183=1, 184=1, 185=3, 186=2, 187=2, 188=1, 189=1, 190=1, 191=1, 192=1, 193=1, 194=1, 195=1, 196=2, 197=2, 198=2, 199=3, 200=3, 201=1, 202=1, 203=1, 204=1, 205=1, 206=1, 207=2, 208=2, 209=2, 210=1, 211=1, 212=1, 213=1, 214=1, 215=1, 216=1, 217=1, 218=2, 219=2, 220=2, 221=1, 222=1, 223=3, 224=2, 225=2, 226=1, 227=1, 228=1, 229=1, 230=1, 231=1, 232=1, 233=1, 234=2, 235=2, 236=2, 237=1, 238=1, 239=1, 240=1, 241=1, 242=1, 243=1, 244=1, 245=2, 246=2, 247=2, 248=1, 249=1, 250=3, 251=2, 252=2, 253=1, 254=1, 255=1, 256=1, 257=1, 258=1, 259=1, 260=1, 261=2, 262=2, 263=2, 264=1, 265=1, 266=3, 267=2, 268=2, 269=1, 270=1, 271=1, 272=2, 273=2, 274=2, 275=1, 276=1, 277=3, 278=2, 279=2, 280=1, 281=1, 282=1, 283=1, 284=1, 285=1, 286=1, 287=1, 288=2, 289=2, 290=2, 291=1, 292=1, 293=1, 294=1, 295=1, 296=2, 297=2, 298=2, 299=2, 300=2, 301=2, 302=3, 303=1, 304=1, 305=1, 306=1, 307=1, 308=1, 309=1, 310=2, 311=2, 312=2, 313=1, 314=1, 315=4, 316=2, 317=2, 318=1, 319=1, 320=1, 321=1, 322=1, 323=1, 324=1, 325=1, 326=2, 327=2, 328=2, 329=1, 330=1, 331=1, 332=1, 333=1, 334=1, 335=1, 336=1, 337=2, 338=2, 339=2, 340=1, 341=1, 342=3, 343=2, 344=2, 345=1, 346=1, 347=1, 348=1, 349=1, 350=1, 351=1, 352=1, 353=2, 354=2, 355=2, 356=1, 357=1, 358=1, 359=1, 360=1, 361=1, 362=1, 363=1, 364=2, 365=2, 366=2, 367=1, 368=1, 369=3, 370=4, 371=4, 372=1, 373=1, 374=1, 375=1, 376=1, 377=1, 378=1, 379=1, 380=2, 381=2, 382=2, 383=1, 384=1, 385=1, 386=1, 387=1, 388=1, 389=1, 390=1, 391=2, 392=2, 393=2, 394=1, 395=1, 396=3, 397=2, 398=2, 399=1, 400=1, 401=1, 402=1, 403=1, 404=1, 405=1, 406=2, 407=2, 408=2, 409=2, 410=1, 411=1, 412=1, 413=1, 414=1, 415=1, 416=1, 417=1, 418=2, 419=2, 420=2, 421=1, 422=1, 423=1, 424=1, 425=1, 426=1, 427=1, 428=1, 429=2, 430=2, 431=2, 432=1, 433=1, 434=3, 435=2, 436=2, 437=1, 438=1, 439=1, 440=1, 441=1, 442=1, 443=1, 444=1, 445=2, 446=2, 447=2, 448=1, 449=1, 450=1, 451=1, 452=1, 453=1, 454=1, 455=1, 456=2, 457=2, 458=2, 459=1, 460=1, 461=3, 462=2, 463=2, 464=1, 465=1, 466=1, 467=1, 468=1, 469=1, 470=1, 471=1, 472=2, 473=2, 474=2, 475=1, 476=1, 477=1, 478=1, 479=1, 480=1, 481=1, 482=1, 483=2, 484=2, 485=2, 486=1, 487=1, 488=3, 489=2, 490=2, 491=1, 492=3, 493=3, 494=2, 495=1, 496=1, 497=1, 498=1, 499=2, 500=2, 501=2, 502=1, 503=1, 504=1, 505=1, 506=1, 507=1, 508=1, 509=1, 510=2, 511=2, 512=2, 513=1, 514=1, 515=1, 516=1, 517=1, 518=1, 519=1, 520=1, 521=2, 522=2, 523=2, 524=1, 525=1, 526=3, 527=2, 528=2, 529=1, 530=1, 531=1, 532=1, 533=1, 534=1, 535=1, 536=1, 537=2, 538=2, 539=2, 540=1, 541=1, 542=1, 543=1, 544=1, 545=1, 546=1, 547=1, 548=2, 549=2, 550=2, 551=1, 552=1, 553=3, 554=2, 555=2, 556=1, 557=1, 558=1, 559=1, 560=1, 561=1, 562=1, 563=1, 564=2, 565=2, 566=2, 567=1, 568=1, 569=1, 570=1, 571=1, 572=1, 573=1, 574=1, 575=2, 576=2, 577=2, 578=1, 579=1, 580=3, 581=2, 582=2, 583=1, 584=1, 585=1, 586=2, 587=2, 588=2, 589=1, 590=2, 591=2, 592=2, 593=2, 594=1, 595=1, 596=1, 597=1, 598=1, 599=1, 600=1, 601=1, 602=2, 603=2, 604=2, 605=1, 606=1, 607=1, 608=1, 609=1, 610=1, 611=1, 612=1, 613=2, 614=2, 615=2, 616=1, 617=1, 618=3, 619=2, 620=2, 621=4, 622=1, 623=1, 624=1, 625=1, 626=1, 627=1, 628=1, 629=2, 630=2, 631=3, 632=1, 633=1, 634=1, 635=1, 636=1, 637=1, 638=1, 639=1, 640=2, 641=2, 642=2, 643=1, 644=1, 645=3, 646=2, 647=2, 648=1, 649=1, 650=3, 651=2, 652=2, 653=1, 654=1, 655=1, 656=2, 657=2, 658=2, 659=1, 660=1, 661=1, 662=1, 663=1, 664=1, 665=1, 666=1, 667=2, 668=2, 669=2, 670=1, 671=1, 672=3, 673=2, 674=2, 675=1, 676=1, 677=1, 678=1, 679=1, 680=1, 681=1, 682=1, 683=2, 684=2, 685=2, 686=1, 687=1, 688=1, 689=1, 690=1, 691=1, 692=1, 693=1, 694=2, 695=2, 696=2, 697=1, 698=1, 699=3, 700=2, 701=2, 702=1, 703=1, 704=1, 705=1, 706=1, 707=1, 708=1, 709=1, 710=2, 711=2, 712=2, 713=1, 714=1, 715=1, 716=1, 717=1, 718=1, 719=1, 720=1, 721=2, 722=2, 723=2, 724=1, 725=1, 726=1, 727=1, 728=1, 729=1, 730=1, 731=1, 732=2, 733=2, 734=2, 735=1, 736=1, 737=3, 738=2, 739=2, 740=1, 741=1, 742=1, 743=1, 744=1, 745=1, 746=1, 747=1, 748=2, 749=2, 750=2, 751=1, 752=1, 753=1, 754=1, 755=1, 756=1, 757=1, 758=1, 759=2, 760=2, 761=2, 762=1, 763=1, 764=3, 765=2, 766=2, 767=1, 768=1, 769=1, 770=1, 771=1, 772=1, 773=1, 774=1, 775=2, 776=2, 777=2, 778=1, 779=1, 780=1, 781=1, 782=1, 783=1, 784=1, 785=1, 786=2, 787=2, 788=2, 789=3, 790=3, 791=3, 792=2, 793=2, 794=1, 795=1, 796=1, 797=1, 798=1, 799=1, 800=1, 801=1, 802=2, 803=2, 804=2, 805=1, 806=1, 807=1, 808=1, 809=1, 810=1, 811=1, 812=1, 813=2, 814=2, 815=2, 816=1, 817=1, 818=1, 819=1, 820=1, 821=1, 822=1, 823=1, 824=2, 825=2, 826=2, 827=1, 828=1, 829=3, 830=2, 831=2, 832=1, 833=1, 834=1, 835=1, 836=1, 837=1, 838=1, 839=1, 840=2, 841=2, 842=2, 843=1, 844=3, 845=3, 846=2, 847=1, 848=1, 849=1, 850=1, 851=2, 852=2, 853=2, 854=1, 855=1, 856=3, 857=2, 858=2, 859=1, 860=1, 861=1, 862=1, 863=1, 864=1, 865=1, 866=1, 867=2, 868=2, 869=2, 870=1, 871=1, 872=1, 873=1, 874=1, 875=1, 876=1, 877=2, 878=2, 879=2, 880=2, 881=1, 882=1, 883=3, 884=2, 885=2, 886=1, 887=1, 888=1, 889=1, 890=1, 891=1, 892=1, 893=1, 894=2, 895=2, 896=2, 897=1, 898=1, 899=1, 900=1, 901=1, 902=1, 903=1, 904=1, 905=2, 906=2, 907=2, 908=1, 909=1, 910=1, 911=1, 912=1, 913=1, 914=1, 915=1, 916=2, 917=2, 918=2, 919=1, 920=1, 921=3, 922=2, 923=2, 924=1, 925=1, 926=1, 927=1, 928=1, 929=1, 930=1, 931=1, 932=2, 933=2, 934=2, 935=3, 936=1, 937=1, 938=1, 939=1, 940=1, 941=1, 942=1, 943=2, 944=2, 945=2, 946=1, 947=1, 948=3, 949=2, 950=2, 951=1, 952=1, 953=1, 954=1, 955=1, 956=1, 957=1, 958=1, 959=2, 960=2, 961=2, 962=1, 963=1, 964=1, 965=1, 966=1, 967=1, 968=1, 969=1, 970=2, 971=2, 972=2, 973=1, 974=1, 975=3, 976=2, 977=2, 978=1, 979=1, 980=1, 981=1, 982=1, 983=1, 984=1, 985=1, 986=2, 987=2, 988=2, 989=1, 990=1, 991=1, 992=1, 993=1, 994=1, 995=1, 996=1, 997=2, 998=2, 999=2, 1000=1, 1001=1";
        HashMap<Integer, Integer> result = Arrays.stream(str.split(","))
                .parallel()
                .map(String::strip)
                .map(s -> s.split("="))
                .collect(
                        Collectors.toMap(
                                s -> Integer.parseInt(s[0]),
                                s -> Integer.parseInt(s[1]),
                                (a, b) -> b,
                                HashMap::new
                        )
                );
        return new Condition(result, policies, policySpecialists);
    }

    /**
     * The method should calculate the department for each specialist.
     * The specialist belongs to the department in which he has the maximum commission on active policies.
     * In
     * A policy is considered active if it has already started but has not yet finished
     *
     * @param policies
     * @param specialists
     * @return key-value map where keys are specialist ids and values are department ids
     */
    @SolutionMethod
    protected abstract Map<Integer, Integer> calculateSpecialistDepartments(
            List<Policy> policies,
            List<PolicySpecialist> specialists
    );
}
