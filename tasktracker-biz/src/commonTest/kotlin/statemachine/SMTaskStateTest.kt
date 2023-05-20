//package ru.rifnur.tasktracker.biz.validation.statemachine
//
//import ru.rifnur.tasktracker.biz.statemachine.SMSTaskSignal
//import ru.rifnur.tasktracker.biz.statemachine.SMSTaskStateResolver
//import kotlin.test.Test
//import kotlin.test.assertContains
//import kotlin.test.assertEquals
//import kotlin.time.Duration.Companion.days
//
//class SMSTaskStateTest {
//
//    @Test
//    fun new2actual() {
//        val machine = SMSTaskStateResolver()
//        val signal = SMSTaskSignal(
//            state = SMSTaskStates.NEW,
//            duration = 4.days,
//            views = 20,
//        )
//        val transition = machine.resolve(signal)
//        assertEquals(SMSTaskStates.ACTUAL, transition.state)
//        assertContains(transition.description, "актуальное", ignoreCase = true)
//    }
//
//    @Test
//    fun new2hit() {
//        val machine = SMSTaskStateResolver()
//        val signal = SMSTaskSignal(
//            state = SMSTaskStates.NEW,
//            duration = 2.days,
//            views = 101,
//        )
//        val transition = machine.resolve(signal)
//        assertEquals(SMSTaskStates.HIT, transition.state)
//        assertContains(transition.description, "Очень", ignoreCase = true)
//    }
//}
