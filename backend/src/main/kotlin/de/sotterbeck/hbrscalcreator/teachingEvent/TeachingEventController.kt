package de.sotterbeck.hbrscalcreator.teachingEvent

import de.sotterbeck.hbrscalcreator.ical.CreateICalInteractor
import org.springframework.http.CacheControl
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.TimeUnit

@RestController
@RequestMapping("/teachingEvents")
class TeachingEventController(
    private val getAllTeachingEventsInteractor: GetAllTeachingEventsInteractor,
    private val getTeachingEventsInteractor: GetTeachingEventsInteractor,
    private val createICalInteractor: CreateICalInteractor
) {

    @GetMapping("/all")
    suspend fun allTeachingEvents(): ResponseEntity<TeachingEventResponse> {
        val teachingEvents = getAllTeachingEventsInteractor()
        return ResponseEntity
            .ok()
            .cacheControl(CacheControl.maxAge(1, TimeUnit.DAYS))
            .body(teachingEvents)
    }

    @GetMapping
    suspend fun teachingEvents(request: GetTeachingEventsInteractor.Request): ResponseEntity<TeachingEventResponse> {
        val teachingEvents = getTeachingEventsInteractor(request)
        return ResponseEntity
            .ok()
            .cacheControl(CacheControl.maxAge(1, TimeUnit.DAYS))
            .body(teachingEvents)
    }

    @GetMapping("/ical")
    suspend fun iCal(request: CreateICalInteractor.Request): ResponseEntity<String> {
        val response = createICalInteractor(request)
        return ResponseEntity
            .ok()
            .headers { it[HttpHeaders.CONTENT_DISPOSITION] = "attachment; filename=${response.fileName}" }
            .body(response.iCal)
    }
}