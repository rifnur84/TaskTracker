package ru.rifnur.tasktracker.mappers.v2.exceptions

import ru.rifnur.tasktracker.common.models.ComTaskCommand

class UnknownComTaskCommand(command: ComTaskCommand) : Throwable("Wrong command $command at mapping toTransport stage")
