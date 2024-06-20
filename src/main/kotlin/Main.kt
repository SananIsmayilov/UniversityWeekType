import java.time.LocalDate
import java.time.temporal.ChronoUnit

fun main(args: Array<String>) {
    val dateToCheck = LocalDate.now() // Bugünün tarihi

    // 15 Eylül ve 16 Şubat tarihlerini belirle
    val referenceDates = listOf(
        LocalDate.of(dateToCheck.year, 9, 15), // Bu yılın 15 Eylül'ü
        LocalDate.of(dateToCheck.year, 2, 16)  // Bu yılın 16 Şubat'ı
    )

    // Referans tarihlerini kontrol edip en yakın olanını bul
    val referenceDate = referenceDates.minByOrNull { date ->
        if (date.isBefore(dateToCheck)) {
            ChronoUnit.DAYS.between(date, dateToCheck)
        } else {
            ChronoUnit.DAYS.between(dateToCheck, date)
        }
    } ?: referenceDates.first()

    val daysBetween = ChronoUnit.DAYS.between(referenceDate.withDayOfMonth(1), dateToCheck).toInt()

    // Haftaların belirlenmesi: 0 = Üst Hafta, 1 = Alt Hafta
    val weekType = if ((daysBetween / 7) % 2 == 0) "Üst Həftə" else "Alt Haftə"

    println("Bugün $dateToCheck ve bu həftə $weekType-dir")
}