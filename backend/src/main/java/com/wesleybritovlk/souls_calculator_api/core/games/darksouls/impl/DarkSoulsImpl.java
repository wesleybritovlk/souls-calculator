package com.wesleybritovlk.souls_calculator_api.core.games.darksouls.impl;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_EVEN;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

import com.wesleybritovlk.souls_calculator_api.app.games.darksouls.dto.DarkSoulsRequest.Souls;
import com.wesleybritovlk.souls_calculator_api.core.common.CommonModel.SoulsNext;
import com.wesleybritovlk.souls_calculator_api.core.games.darksouls.DarkSouls;

@Component
public class DarkSoulsImpl implements DarkSouls {

    private static BigDecimal decimal(Number num) {
        return BigDecimal.valueOf(num.doubleValue());
    }

    private static final int[] DOCUMENTED_SOULS_REQUIRED = new int[] {
            0, 673, 690, 707, 724, 741, 758, 775, 793, 811, 829, 847,
            1039, 1238, 1445, 1660, 1883, 2114, 2353, 2601, 2857, 3122, 3396, 3678, 3970, 4271, 4581, 4900, 5229, 5567,
            5915, 6273, 6641, 7019, 7407, 7805, 8214, 8634, 9064, 9505, 9957, 10420, 10894, 11379, 11876, 12384, 12904,
            13436, 13979, 14535, 15103, 15683, 16275, 16880, 17497, 18127, 18770, 19426, 20095, 20777, 21472, 22181,
            22904, 23640, 24390, 25154, 25932, 26724, 27530, 28351, 29189, 30036, 30901, 31780, 32675, 33585, 34510,
            35450, 36406, 37377, 38364, 39367, 40386, 41421, 42472, 43539, 44623, 45724, 46841, 47975, 49126, 50294,
            51479, 52681, 53901, 55138, 56393, 57666, 58956, 60265, 61592, 62937, 64300, 65682, 67082, 68501, 69939,
            71396, 72872, 74367, 75881, 77415, 78969, 80542, 82135, 83748, 85381, 87034, 88707, 90401, 92115, 93850,
            95606, 97382, 99180, 100999, 102839, 104700, 106583, 108487, 110413, 112361, 114331, 116323, 118337, 120373,
            122432, 124514, 126618, 128745, 130895, 133068, 135264, 137483, 139726, 141992, 144282, 146596, 148933,
            151295, 153681, 156091, 158525, 160984, 163467, 165975, 168508, 171066, 173649, 176257, 178890, 181549,
            184234, 186944, 189680, 192442, 195230, 198044, 200884, 203751, 206644, 209564, 212511, 215484, 218485,
            221513, 224568, 227650, 230760, 233897, 237062, 240255, 243476, 246725, 250002, 253307, 256641, 260004,
            263395, 266815, 270264, 273742, 277249, 280785, 284351, 287946, 291571, 295226, 298910, 302625, 306370,
            310145, 313950, 317786, 321652, 325549, 329477, 333436, 337426, 341447, 345449, 349583, 353699, 357846,
            362025, 366236, 370479, 374754, 379061, 383401, 387773, 392178, 396616, 401086, 405590, 410127, 414697,
            419300, 423937, 428607, 433311, 438049, 442821, 447627, 452467, 457341, 462250, 467194, 472172, 477185,
            482233, 487316, 492434, 497587, 502776, 508000, 513260, 518556, 523887, 529255, 534659, 540099, 545575,
            551088, 556637, 562223, 567846, 573506, 579203, 584937, 590709, 596517, 602364, 608248, 614170, 620130,
            626128, 632164, 638238, 644351, 650502, 656692, 662921, 669188, 675495, 681841, 688226, 694650, 701114,
            707617, 714160, 720743, 727366, 734029, 740732, 747476, 754259, 761084, 767949, 774855, 781802, 788790,
            795819, 802889, 810001, 817154, 824349, 831586, 838864, 846185, 853548, 860953, 868400, 875890, 883422,
            890997, 898615, 906276, 913980, 921727, 929517, 937351, 945229, 953150, 961115, 969124, 977177, 985274,
            993415, 1001601, 1009831, 1018196, 1026426, 1034790, 1039638, 1049316, 1060156, 1068700, 1077291, 1085927,
            1094609, 1103337, 1112111, 1120931, 1129797, 1138710, 1147668, 1156674, 1165726, 1174825, 1183971, 1193164,
            1202404, 1211691, 1221026, 1230408, 1239838, 1249316, 1258841, 1268415, 1278037, 1287707, 1297425, 1307192,
            1317007, 1326871, 1336784, 1346746, 1356757, 1366817, 1376927, 1387085, 1397294
    };

    private static BigDecimal selectDocumentedSouls(BigDecimal level) {
        return decimal(DOCUMENTED_SOULS_REQUIRED[level.subtract(ONE).intValue()]);
    }

    private static BigDecimal calculateWithSoulsForumla(BigDecimal level) {
        BigDecimal firstCoeff = decimal(0.02),
                secondCoeff = decimal(3.06),
                thirdCoeff = decimal(105.6),
                constant = decimal(895);
        return firstCoeff.multiply(level.pow(3))
                .add(secondCoeff.multiply(level.pow(2)))
                .add(thirdCoeff.multiply(level))
                .subtract(constant);
    }

    @Override
    public SoulsNext calculateSouls(Souls request) {
        var arrowLevel = decimal(request.arrowLevel());
        var documentedLength = decimal(DOCUMENTED_SOULS_REQUIRED.length);
        var amount = IntStream.rangeClosed(1, arrowLevel.intValue())
                .parallel().mapToObj(level -> {
                    var levelDecimal = decimal(level);
                    return (levelDecimal.compareTo(ONE) >= 0 && levelDecimal.compareTo(documentedLength) <= 0)
                            ? selectDocumentedSouls(levelDecimal)
                            : calculateWithSoulsForumla(levelDecimal);
                }).reduce(ZERO, BigDecimal::add);
        BigInteger soulsNext = amount.setScale(0, HALF_EVEN).toBigInteger(),
                currentSouls = request.currentSouls();
        if (soulsNext.compareTo(currentSouls) <= 0)
            return new SoulsNext(BigInteger.ZERO);
        if (soulsNext.compareTo(currentSouls) >= 0)
            soulsNext = soulsNext.subtract(currentSouls);
        return new SoulsNext(soulsNext);
    }
}
