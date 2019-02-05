/*
 * This file is part of text, licensed under the MIT License.
 *
 * Copyright (c) 2017-2019 KyoriPowered
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package net.kyori.text;

import net.kyori.text.event.ClickEvent;
import net.kyori.text.event.HoverEvent;
import net.kyori.text.format.TextColor;
import net.kyori.text.format.TextDecoration;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;

import static java.util.Objects.requireNonNull;

public class KeybindComponent extends AbstractBuildableComponent<KeybindComponent, KeybindComponent.Builder> {
  /**
   * The keybind.
   */
  private final String keybind;

  /**
   * Creates a keybind component builder.
   *
   * @return a builder
   */
  public static @NonNull Builder builder() {
    return new Builder();
  }

  /**
   * Creates a keybind component builder with a keybind.
   *
   * @param keybind the keybind
   * @return a builder
   */
  public static @NonNull Builder builder(final @NonNull String keybind) {
    return new Builder().keybind(keybind);
  }

  /**
   * Creates a keybind component with a keybind.
   *
   * @param keybind the keybind
   * @return the keybind component
   */
  public static @NonNull KeybindComponent of(final @NonNull String keybind) {
    return builder(keybind).build();
  }

  /**
   * Creates a keybind component with content, and optional color.
   *
   * @param keybind the keybind
   * @param color the color
   * @return the keybind component
   */
  public static @NonNull KeybindComponent of(final @NonNull String keybind, final @Nullable TextColor color) {
    return of(keybind, color, Collections.emptySet());
  }

  /**
   * Creates a keybind component with content, and optional color and decorations.
   *
   * @param keybind the keybind
   * @param color the color
   * @param decorations the decorations
   * @return the keybind component
   */
  public static @NonNull KeybindComponent of(final @NonNull String keybind, final @Nullable TextColor color, final @NonNull Set<TextDecoration> decorations) {
    return builder(keybind).color(color).decorations(decorations, true).build();
  }

  /**
   * Creates a keybind component by applying configuration from {@code consumer}.
   *
   * @param consumer the builder configurator
   * @return the keybind component
   */
  public static @NonNull KeybindComponent make(final @NonNull Consumer<Builder> consumer) {
    final Builder builder = builder();
    consumer.accept(builder);
    return builder.build();
  }

  /**
   * Creates a keybind component by applying configuration from {@code consumer}.
   *
   * @param keybind the keybind
   * @param consumer the builder configurator
   * @return the keybind component
   */
  public static @NonNull KeybindComponent make(final @NonNull String keybind, final @NonNull Consumer<Builder> consumer) {
    final Builder builder = builder(keybind);
    consumer.accept(builder);
    return builder.build();
  }

  protected KeybindComponent(final @NonNull Builder builder) {
    super(builder);
    this.keybind = builder.keybind;
  }

  protected KeybindComponent(final @NonNull List<Component> children, final @Nullable TextColor color, final TextDecoration.@NonNull State obfuscated, final TextDecoration.@NonNull State bold, final TextDecoration.@NonNull State strikethrough, final TextDecoration.@NonNull State underlined, final TextDecoration.@NonNull State italic, final @Nullable ClickEvent clickEvent, final @Nullable HoverEvent hoverEvent, final @Nullable String insertion, final @NonNull String keybind) {
    super(children, color, obfuscated, bold, strikethrough, underlined, italic, clickEvent, hoverEvent, insertion);
    this.keybind = keybind;
  }

  /**
   * Gets the keybind.
   *
   * @return the keybind
   */
  public @NonNull String keybind() {
    return this.keybind;
  }

  /**
   * Sets the keybind.
   *
   * @param keybind the keybind
   * @return a copy of this component
   */
  public @NonNull KeybindComponent keybind(final @NonNull String keybind) {
    return new KeybindComponent(this.children, this.color, this.obfuscated, this.bold, this.strikethrough, this.underlined, this.italic, this.clickEvent, this.hoverEvent, this.insertion, requireNonNull(keybind, "keybind"));
  }

  @Override
  public @NonNull KeybindComponent append(final @NonNull Component component) {
    this.detectCycle(component); // detect cycle before modifying
    final List<Component> children = new ArrayList<>(this.children.size() + 1);
    children.addAll(this.children);
    children.add(component);
    return new KeybindComponent(children, this.color, this.obfuscated, this.bold, this.strikethrough, this.underlined, this.italic, this.clickEvent, this.hoverEvent, this.insertion, this.keybind);
  }

  @Override
  public @NonNull KeybindComponent color(final @Nullable TextColor color) {
    return new KeybindComponent(this.children, color, this.obfuscated, this.bold, this.strikethrough, this.underlined, this.italic, this.clickEvent, this.hoverEvent, this.insertion, this.keybind);
  }

  @Override
  public @NonNull KeybindComponent decoration(final @NonNull TextDecoration decoration, final boolean flag) {
    return (KeybindComponent) super.decoration(decoration, flag);
  }

  @Override
  public @NonNull KeybindComponent decoration(final @NonNull TextDecoration decoration, final TextDecoration.@NonNull State state) {
    switch(decoration) {
      case BOLD: return new KeybindComponent(this.children, this.color, this.obfuscated, requireNonNull(state, "flag"), this.strikethrough, this.underlined, this.italic, this.clickEvent, this.hoverEvent, this.insertion, this.keybind);
      case ITALIC: return new KeybindComponent(this.children, this.color, this.obfuscated, this.bold, this.strikethrough, this.underlined, requireNonNull(state, "flag"), this.clickEvent, this.hoverEvent, this.insertion, this.keybind);
      case UNDERLINED: return new KeybindComponent(this.children, this.color, this.obfuscated, this.bold, this.strikethrough, requireNonNull(state, "flag"), this.italic, this.clickEvent, this.hoverEvent, this.insertion, this.keybind);
      case STRIKETHROUGH: return new KeybindComponent(this.children, this.color, this.obfuscated, this.bold, requireNonNull(state, "flag"), this.underlined, this.italic, this.clickEvent, this.hoverEvent, this.insertion, this.keybind);
      case OBFUSCATED: return new KeybindComponent(this.children, this.color, requireNonNull(state, "flag"), this.bold, this.strikethrough, this.underlined, this.italic, this.clickEvent, this.hoverEvent, this.insertion, this.keybind);
      default: throw new IllegalArgumentException(String.format("unknown decoration '%s'", decoration));
    }
  }

  @Override
  public @NonNull KeybindComponent clickEvent(final @Nullable ClickEvent event) {
    return new KeybindComponent(this.children, this.color, this.obfuscated, this.bold, this.strikethrough, this.underlined, this.italic, event, this.hoverEvent, this.insertion, this.keybind);
  }

  @Override
  public @NonNull KeybindComponent hoverEvent(final @Nullable HoverEvent event) {
    if(event != null) this.detectCycle(event.value()); // detect cycle before modifying
    return new KeybindComponent(this.children, this.color, this.obfuscated, this.bold, this.strikethrough, this.underlined, this.italic, this.clickEvent, event, this.insertion, this.keybind);
  }

  @Override
  public @NonNull KeybindComponent insertion(final @Nullable String insertion) {
    return new KeybindComponent(this.children, this.color, this.obfuscated, this.bold, this.strikethrough, this.underlined, this.italic, this.clickEvent, this.hoverEvent, insertion, this.keybind);
  }

  @Override
  public @NonNull KeybindComponent mergeStyle(final @NonNull Component that) {
    return new KeybindComponent(this.children, that.color(), that.decoration(TextDecoration.OBFUSCATED), that.decoration(TextDecoration.BOLD), that.decoration(TextDecoration.STRIKETHROUGH), that.decoration(TextDecoration.UNDERLINED), that.decoration(TextDecoration.ITALIC), that.clickEvent(), that.hoverEvent(), that.insertion(), this.keybind);
  }

  @Override
  public @NonNull KeybindComponent mergeColor(final @NonNull Component that) {
    return new KeybindComponent(this.children, that.color(), this.obfuscated, this.bold, this.strikethrough, this.underlined, this.italic, this.clickEvent, this.hoverEvent, this.insertion, this.keybind);
  }

  @Override
  public @NonNull KeybindComponent mergeDecorations(final @NonNull Component that) {
    final TextDecoration.State obfuscated = that.decoration(TextDecoration.OBFUSCATED) != TextDecoration.State.NOT_SET ? that.decoration(TextDecoration.OBFUSCATED) : this.obfuscated;
    final TextDecoration.State bold = that.decoration(TextDecoration.BOLD) != TextDecoration.State.NOT_SET ? that.decoration(TextDecoration.BOLD) : this.bold;
    final TextDecoration.State strikethrough = that.decoration(TextDecoration.STRIKETHROUGH) != TextDecoration.State.NOT_SET ? that.decoration(TextDecoration.STRIKETHROUGH) : this.strikethrough;
    final TextDecoration.State underlined = that.decoration(TextDecoration.UNDERLINED) != TextDecoration.State.NOT_SET ? that.decoration(TextDecoration.UNDERLINED) : this.underlined;
    final TextDecoration.State italic = that.decoration(TextDecoration.ITALIC) != TextDecoration.State.NOT_SET ? that.decoration(TextDecoration.ITALIC) : this.italic;
    return new KeybindComponent(this.children, this.color, obfuscated, bold, strikethrough, underlined, italic, this.clickEvent, this.hoverEvent, this.insertion, this.keybind);
  }

  @Override
  public @NonNull KeybindComponent mergeEvents(final @NonNull Component that) {
    return new KeybindComponent(this.children, this.color, this.obfuscated, this.bold, this.strikethrough, this.underlined, this.italic, that.clickEvent(), that.hoverEvent(), this.insertion, this.keybind);
  }

  @Override
  public @NonNull KeybindComponent resetStyle() {
    return new KeybindComponent(this.children, null, TextDecoration.State.NOT_SET, TextDecoration.State.NOT_SET, TextDecoration.State.NOT_SET, TextDecoration.State.NOT_SET, TextDecoration.State.NOT_SET, null, null, null, this.keybind);
  }

  @Override
  public @NonNull KeybindComponent copy() {
    return new KeybindComponent(this.children, this.color, this.obfuscated, this.bold, this.strikethrough, this.underlined, this.italic, this.clickEvent, this.hoverEvent, this.insertion, this.keybind);
  }

  @Override
  public boolean equals(final @Nullable Object other) {
    if(this == other) return true;
    if(other == null || !(other instanceof KeybindComponent)) return false;
    if(!super.equals(other)) return false;
    final KeybindComponent component = (KeybindComponent) other;
    return Objects.equals(this.keybind, component.keybind);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), this.keybind);
  }

  @Override
  protected void populateToString(final @NonNull Map<String, Object> builder) {
    builder.put("keybind", this.keybind);
  }

  @Override
  public @NonNull Builder toBuilder() {
    return new Builder(this);
  }

  /**
   * A keybind component builder.
   */
  public static class Builder extends AbstractBuildableComponent.AbstractBuilder<KeybindComponent, Builder> {
    private @Nullable String keybind;

    Builder() {
    }

    Builder(final @NonNull KeybindComponent component) {
      super(component);
      this.keybind = component.keybind();
    }

    /**
     * Sets the keybind.
     *
     * @param keybind the keybind
     * @return this builder
     */
    public @NonNull Builder keybind(final @NonNull String keybind) {
      this.keybind = keybind;
      return this;
    }

    @Override
    public @NonNull KeybindComponent build() {
      if(this.keybind == null) throw new IllegalStateException("keybind must be set");
      return new KeybindComponent(this);
    }
  }
}
