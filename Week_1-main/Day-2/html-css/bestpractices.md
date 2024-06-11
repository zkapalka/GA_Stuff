
## HTML Best Practices

### Forms and Inputs

Often websites need to get input from the user - names, addresses, and other
information. When using the `<input>` element, it should ALWAYS be wrapped in
a `<form>`. This is helpful both for accessibility (such as screen readers)
and for accessing that data using JavaScript.

For accessibility, `<input>` elements should have an `id` attribute, which is
used in combination with the `for` attribute on a `<label>`:

```html
<label for="lastname">Last Name</label>
<input type="text" name="lastName" id="lastname">
```

### Images

Images should include an `alt` tag to describe the image for screen readers.

```html
<img src="public/puppy.png" alt="Cute puppy dog playing with ball in grass."/>
```

## CSS Best Practices

### Selectors

1. AVOID INLINE STYLES. It's best to keep all your styles in one place, within
one or more stylesheets.

1. USE IDs SPARINGLY. IDs should be reserved for those rare situations when
you need to style an element _differently from every other element in your site_.
There are a lot of selectors to use; it's rarely necessary to use an ID.

[CSS Selectors Cheat Sheet](https://gist.github.com/magicznyleszek/809a69dd05e1d5f12d01)

### Colors

There are 6 different ways to specify color in CSS. They are: keyword,
hexadecimal, RGB, RGBA, HSL, and HSLA. All of these formats are reduced to
RGB/A by the browser. In general, we want to use hexadecimal colors, whenever
we aren't specifying transparency using the _alpha channel_ provided in the
RGBA or HSLA formats.

```css
.article-header {
  background-color: #008000;
  /* is better than */
  background-color: green;
}
```

### Fonts

We don't know what fonts are installed on our users' computers, so we always
specify a generic font family as a back up (i.e., `san-serif` or `serif`). The
generic should be the last in the list of supplied `font-family` values. Note
that specific font names are quoted, but the generic is not.

```css
.site-nav {
  <-- If there's no Roboto available try Arial
    If there's no Arial use the default sans-serif
    font installed on the system. -->
  font-family: 'Roboto', 'Arial', sans-serif;
}
```

### CSS Units

There are different [CSS units](https://developer.mozilla.org/en-US/docs/Learn/CSS/Introduction_to_CSS/Values_and_units) to use for handling sizing elements,
fonts, and more. Here's a quick overview of them and some general hints about when we use them:

|  Unit  |  Description  |   Usage  |
|:----------:|:----------------:|:------------:|
|   px   | Referred to as absolute units because px (pixel) units will always be the same size regardless of any other related settings. | Most frequently with font sizes, margins, padding, max- and min- properties. |
|  em, rem | Relative to the current element's font-size (em), or the _root_ font-size (rem). When used to set the font-size of an element, em is relative to the element's inherited size. | Most frequently with font sizes, margins, padding, widths or heights that may need to be changed but remain relative to other elements. |
|  vh, vw  | Relative to the viewport width (vw), where one unit is equal to 1/100th of the viewport's current width, or the viewport height (vh), where one unit is equal to 1/100th of the viewport's current height. | Most frequently with width and height of structural page elements. Commonly used in responsive design. |
|   %   | Percentages are relative values. What the percentage is relative to, is determined by the property associated with the percentage value. | Most often used with width. Can be helpful to approximate intrisic sizing. |

### Organizing Your CSS File

As more and more rulesets are added to your CSS file, working within it can
quickly become a challenge. Here are some things that can help make you more
efficient when working with CSS:

1. **Take advantage of cascade, Don't fight it.** Organize the contents of your
CSS files from the least specific to the most specific selectors. Generally,
this means the universal selector and tag selectors belong at the top of the
page, followed by class and attribute selectors, id selectors, and lastly,
media queries.

1. **Alphabetize your rulesets and declarations.** While it takes a bit more
effort initially, alphabetizing your rulesets and rules will save you loads
of time finding specific rules when you need to modify them.

1. **Break up large files into smaller ones.** The [`@import` CSS-rule](https://developer.mozilla.org/en-US/docs/Web/CSS/@import)
allows us to break up our CSS files into smaller, more manageable ones.
Cascade still applies, so the order in which they are imported matters.
One technique is to have a base file containing all of your tag selector
styles, and then break up the remaining rulesets into component files
(e.g., footer styles, form styles) and media query files.

1. **Use CSS variables.** [CSS variables (AKA, custom properties)](https://developer.mozilla.org/en-US/docs/Web/CSS/Using_CSS_variables)
make it possible to reuse values throughout your CSS. This is not only
easier than remembering or looking up values each time you need to use them,
it can make implementing sitewide changes a relatively straightforward exercise.

1. **Learn to use the developer tools.** The developer tools in the browser will
show you which rules override others; will often provide the exact filename and
line number for the rules you want to edit; and allow you to experiment with
styles in a non-destructive way.
