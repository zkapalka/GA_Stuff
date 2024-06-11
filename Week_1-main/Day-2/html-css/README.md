[![General Assembly Logo](https://camo.githubusercontent.com/1a91b05b8f4d44b5bbfb83abac2b0996d8e26c92/687474703a2f2f692e696d6775722e636f6d2f6b6538555354712e706e67)](https://generalassemb.ly/education/web-development-immersive)

# HTML & CSS Overview

Let's go over the basics of HTML and CSS! Most of you should have some
experience with this stuff already, since you completed the
[myGA](https://my.generalassemb.ly/) pre-work and each built a simple website
as part of your admissions process.

## Objectives

Developers should, at the end of the lesson, be able to:

- Write out the basic skeleton of an HTML page.
- Add CSS to an HTML file by linking to an external stylesheet with `<link>`.
- Explain at a high level how CSS styling works.
- Write CSS and use it to add styling to a basic page.

## Preparation

1. [Fork and clone](https://git.generalassemb.ly/ga-wdi-boston/meta/wiki/ForkAndClone) this repository.
1. Create a new branch, `training`, for your work.

## HTML

HTML defines the structure and content of information on the page.

All HTML pages have the same basic structure:

```html
<!DOCTYPE html>
<html>
  <head>
  <!-- Meta-data goes here. -->
  </head>
  <body>
   <!-- Page content goes here. -->
  </body>
</html>
```

### Semantic Tags

HTML tags generally come in matched pairs, with the format `<tag> ... </tag>`.
The first tag is called the _opening tag_, while the second is called the
_closing tag_.

HTML5 encourages the use of _semantic_ tags whose names reflect their content
and role within the page.

Some of the new elements that HTML5 added are: `<section>`, `<header>`, `<nav>`,
`<footer>`, and `<main>`. These tags help us to structure our content better
and vastly improve accessibility for those who experience our content in
non-visual ways.

### Block and Inline Elements

Historically, elements could be classified as either: **block** elements or
**inline** elements.

Block elements have built-in line breaks, causing them
to automatically _stack vertically_, while inline elements wrap within their
containing elements. One way we can distinguish block elements from inline
elements is to think of block elements as relating to parts of the page,
creating "larger" structures than inline elements.

![blockvsinline](https://media.git.generalassemb.ly/user/16103/files/25a9d380-6229-11eb-9c8c-d59909b0d57e)

Even though the HTML5 specification provides different categories for elements,
by default, all elements behave by either creating a new line or wrapping
inside of other elements. These two characteristics are important to
understand because they determine how elements can be styled.

Inline elements are only as large as their contents. This means they cannot
have `width` or `height` set with CSS (with one exception: `<img>` is
technically an inline element), and `margin` and `padding` can only be
applied to the left or right of the element. Inline elements can also
be aligned within a containing block element that has the `text-align`
property set on it (including `<img>` elements).

Examples of block and inline elements:

|  Block  |  Inline  |
|:-----------:|:------------:|
|  `<div>`  |  `<span>`  |
| `<article>` |  `<input>` |
| `<header>` | `<strong>` |
|  `<p>`  |   `<a>`  |

### Attributes

HTML attributes describe certain behavior or settings for a given HTML element.

All HTML elements support attributes of different kinds, but many attributes
can only be used on certain elements. Attributes always live within the opening
tag of an HTML element.

For example:

```html
<a href="http://google.com">Google</a>
```

Here, `href="http://google.com"` is an attribute.

Attributes will always follow the `attribute-name="value"` convention.

Below are a list of extremely helpful attributes that allow you to add
custom meta-information to your HTML elements. They become immensely helpful
when targeting these elements with CSS and/or JavaScript (we'll see this
more in jQuery).

| Attribute |   Usage  |
|:-----------:|:------------:|
| [`id`](https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes/id) | Value can only be used once, elements can only have max of one ID. Creates a unique identifier for an element. |
| [`class`](https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes/class) | Value can be used multiple times, elements can have many classes. |
| [`data-*`](https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes/data-%2A) | Very helpful when used with the CSS content property and jQuery. Allows data to be bound to HTML element using custom `data-<custom>="<custom value>"` convention. |

The above attributes are globally-applicable, however some attributes only work
or make sense on certain elements. Here are some examples:

| Attribute |   Usage  |
|:-----------:|:------------:|
| [`src`](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/img#attributes) | Location of an image to be displayed with an `img` tag. |
| [`placeholder`](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#attributes) | A value to display inside of an `input` tag when there is no value yet (before the user types). |

You'll notice the links for `src` and `placeholder` bring us to the
documentation for individual tags, like the `img` and `input` tags. This is
because these attributes do not exist globally for all elements.

### Code-Along: Valerie's Veggie Shop

Together we are going to look at our good friend Valerie's proposal for her
website. Valerie has been so gracious to provide us with what she wants to be
included on her [website](https://pages.git.generalassemb.ly/ga-wdi-boston/html-css/).
Here are the steps we'll take to build her site:

1. Run the webpage with the command `npm run serve`

1. Review the contents and map out our structure.

1. Markup our [index.html](index.html) file using semantic tags.

## CSS

In the early days of the web, people used to style their pages using explicit
styling tags such as `<font>`, `<center>` and `<strike>` (all of which have
long been **deprecated** and should never be used today). This was inflexible,
difficult to maintain, and conflated _presentation_ with the document structure.

CSS emerged in the mid-90s as a way to make styling webpages easier. It's
core idea was to replace explicit styling in HTML with _styling rules_ which
could be applied to multiple elements; this would have the benefits of (a)
reducing duplication, and (b) separating styling instructions from content.

### Basic Syntax

CSS works by selecting some group of elements (using a special reference called
a **selector**) and defining a set of properties and values to apply to that
group of elements. The general syntax for this is:

```css
selector {
  property: value;
  property: value;
}
```

A specific example is

```css
.article-header {
  height: 100px;
  width: 100px;
  background-color: green;
}
```

- This looks similar to a JS object literal; however, one important difference
- is that key-value pairs are separated by _semicolons_ instead of _commas_.

### Properties

There are many, many CSS properties available to us. We will touch on some
important properties to know about, but we'll also want to get comfortable
searching for what we need using the [CSS property documentation](https://developer.mozilla.org/en-US/docs/Web/CSS/Reference#index).

#### Color

We can declare the color of text inside a given element with the `color` property. To declare background color, we can use `background` or `background-color`.

```css
.article-header {
  /* Sets font color to black using a hex code */
  color: #000000;
  /* Sets background color to white using a hex code */
  background-color: #ffffff;
}
```

We can define colors many ways, but you will often see the use of hex codes, or
hexadecimal codes, which represent the red, green, and blue values of the color
using letters and numbers.

You don't need to memorize these codes, instead there are many resources to use
to find the hexadecimal code of a given color, such as the [MDN Color Keywords Hex Code Table](https://developer.mozilla.org/en-US/docs/Web/CSS/color_value#color_keywords).

#### Fonts

We set fonts using the `font-family` property. In addition to the built-in fonts, you can also find fonts using Google Fonts or other services.

When defining a font, you can use family names, such as `'Garamond'`, or generic
names, like `serif`.

```css
.article-header {
  /* Sets the font-family to use 'Garamond'. If that font cannot be loaded,
  uses the generic serif font as a default. */
  font-family: 'Garamond', serif;
}
```

#### Sizing

We will learn more about layout with CSS, but for now we should know about
the `width` and `height` properties, used to define the sizes of different
elements.

_Note: Keep in mind that we cannot use these properties on inline elements._

```css
.article-header {
  /* Provided this is a block element, sets the width to 200px and the
  height to 300px */
  width: 200px;
  height: 300px;
}
```

### Last CSS Rule Standing

Since CSS is just a collection of style rules, one key concern is: what happens
if two rules disagree? CSS has two mechanisms to resolve these disagreements
when they come up.

#### Specificity

The first is that, if two rules disagree about the value that a property
(e.g. 'background-color') should have, a property called **specificity**
can be calculated for each selector, and the selector with higher specificity
will win out.

The short version of how specificity works is that IDs are
more 'specific' than classes, which are more 'specific' than tags, which are
more 'specific' than properties inherited from parent elements.

> Specificity is actually a very precise calculation:
>   +1000pts for each inline style attribute
>   +100pts for each ID
>   +10pts for each attribute, class, or pseudo-class
>   +1pt for each element or pseudo-element tag

#### Cascading

The second mechanism handles when two _equally specific_ rules disagree.
In that case, the last rule that is read by the browser wins! This kind of
behavior is called "cascading", and is where the "C" in CSS comes from.

### Adding CSS to HTML

To add CSS to a page, either include it

1. Inline, within an element's opening tag as a `style` attribute.
```html
<button style="color: blue">Click me</button>
```

1. Between two `<style>` tags, typically in the the `<head>` of the document.

1. **Most Common** In a separate file referred to by a `<link>` tag, also
typically in the the `<head>`. The syntax for using a `<link>` tag is
`<link rel="stylesheet" type="text/css" href="...">` where 'href' is set to
the location of the desired stylesheet.

_Note: We will be using separate files to write 99% of our CSS styles._

## Code-along: Style Valerie's Veggies

Let's apply your styling knowledge to Valerie's veggies website. As a reminder,
[this is what we're going for](https://pages.git.generalassemb.ly/ga-wdi-boston/html-css/).

## Lab: Best Practices

In groups, walk through the [bestpractices.md](bestpractices.md) file to read
about HTML and CSS best practices.

## Bonus: Portfolio Website

Follow the instructions to complete the [portfolio site lab](https://git.generalassemb.ly/eron-salling/html-portfolio-lab).

## Additional Resources

Here are some sites you might want to bookmark, if you haven't already.

- [HTML5 Cheatsheet](http://htmlcheatsheet.com/)
- [CSS Cheatsheet](http://htmlcheatsheet.com/css/)
- [Semantics in HTML](https://developer.mozilla.org/en-US/docs/Glossary/Semantics#semantics_in_html)
- [HTML5 Element Flowchart](http://html5doctor.com/downloads/h5d-sectioning-flowchart.pdf)
- [Introduction to HTML](https://developer.mozilla.org/en-US/docs/Learn/HTML/Introduction_to_HTML)
- [Introduction to CSS](https://developer.mozilla.org/en-US/docs/Learn/CSS/Introduction_to_CSS)
- [CSS specificity calculator](http://specificity.keegan.st/)

## [License](LICENSE)

1. All content is licensed under a CC­BY­NC­SA 4.0 license.
1. All software code is licensed under GNU GPLv3. For commercial use or
  alternative licensing, please contact legal@ga.co.
