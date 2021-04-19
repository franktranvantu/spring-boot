package com.franktran.commondto.event;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public abstract class Event {

  protected final UUID eventId = UUID.randomUUID();
  protected final LocalDateTime date = LocalDateTime.now();

}
