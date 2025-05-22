package de.balloncon.cedh_tool_backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;

// TODO: find a way to hide the loss option for the API-doc
@Schema(
    description =
        "Only win and draw are valid options at the API level, while loss is only used internally")
public enum Result {
  win,
  draw,
  loss,
  bye
}
