# HTML & CSS

## Learning Objectives

- Review the roles of HTML and CSS in web pages
- Distinguish in-line styles, style tags, and linked style sheets
- Break down the syntax of a CSS declaration and a CSS rule(set)
- List commonly used properties
- Distinguish the components of the box model

## Overview (5 minutes / 0:05)

This lesson will be a refresher on the fundamentals of HTML and CSS. All
material is review from the pre-work, so we will move quickly and potentially
glosses over material. For a much more robust treatment, please see [the Mozilla
Developer Network Learning
Area](https://developer.mozilla.org/en-US/docs/Learn).

### Discussion Questions

- What are the main 3 languages that are used to create a web page?  
- What are their general roles in how a webpage displays information?

## CSS (Cascading Style Sheets)

We use CSS to tell browsers how we would like for them to **display** the
elements of our document.

### Websites without CSS (10 minutes / 2:00)

So far the website we've created together has no CSS and is a little plain.

Let's look at some websites and take away their CSS and see how dull and plain
they become.  Note that the content will still be the same, just the *styling*
will be different.  

- Now lets checkout [CSS Zen Garden](http://www.csszengarden.com) to see some
  examples of the great power and diversity that CSS can have on a website.  

> Optional: You can use the chrome extension called [web developer](https://chrome.google.com/webstore/detail/web-developer/bfbameneiokkgbdmiekhjnmfkcnldhhm?hl=en-US) to disable css on any site. If you want to.

### CSS File: We do (5 minutes / 1:55)

To get started writing styles we will create a new file.

```bash
touch ~/sei/tmp/html-and-css/style.css
```
> Notice: this is an absolute path. If you're in the `html-and-css` directory
> you can just `touch style.css`

Then we will add a line to our HTML linking the stylesheet.

```html
<link rel="stylesheet" href="style.css">
```

We can test to make sure the style sheet is linked by adding a rule (we'll break
down rules momentarily):

```css
body {
  background: lemonchiffon;
}
```

When we refresh the page, we should see the background color change.

Note: There are three ways to write CSS into your HTML file.

- **Inline** (Good) ==> `<p style="background: blue;">Inline Example</p> `
- **Internal** (Better) ==> `<style>` element in `<head>` of html file
- **External** (Best!) ==> linking an external CSS file

### CSS Rules (5 minutes / 2:00)

CSS styles are a series of **rules** or **rulesets**. A rule is a combination of
a **selector** and a set of **declarations**.

![Anatomy of a CSS
Ruleset](https://mdn.mozillademos.org/files/9461/css-declaration-small.png)

## Selector (10 minutes / 2:10)

A selector is a pattern used to match HTML elements to the rule that should
apply. As shown, a selector can be an element. Or, very commonly, we add `class`
or `id` attributes to mark elements for targeting by a specific rule.

- Periods '.' are used to select a class like this

```css
.class-name {
  color: red;
}
```

- Hashes '#' are used to select an id like this

```css
#id-name {
  color: blue;
}
```

Note: CSS rules that are **More Specific** will override rules that are less
specific.

- Targeting parent element ==> *Least specific*
- Targeting element directly ==> *Less specific*
- Targeting class ==> *More specific*
- Targeting id ==> *Most specific*

CSS can also overwrite itself. For example, if you have two rules that have the same precedence (like two classes), the one that appears further down in the file will overwrite the previous one.

### Classes/IDs: I Do (5 min / 2:15)

Add the following to HTML file:

```html
<p>Paragraph 1</p>
<p class="paragraph">Paragraph 2</p>
<p id="p3">Paragraph 3</p>
<p id="p4" class="paragraph">Paragraph 4</p>
```

Then add this to the CSS file:

```css
p {
  color: red;
  font-size: 24px;
}
.paragraph {
  color: purple;
  font-size: 36px;
}
#p3 {
  color: blue;
}
#p4 {
  color: yellow;
}
```

Selectors can be combined and related and there are many more types of
[selectors](https://developer.mozilla.org/en-US/docs/Learn/CSS/Introduction_to_CSS/Selectors).

**Bonus**: Especially interesting are [pseudo class
selectors](https://developer.mozilla.org/en-US/docs/Learn/CSS/Introduction_to_CSS/Pseudo-classes_and_pseudo-elements).

### Exercise: You do [CSS Diner](http://flukeout.github.io/) (20 min / 2:20)

- 15 minutes working / 5 minutes review

### Declaration (10 minutes / 2:30)

A declaration has two parts, a property and a value to which that property
should be set. In the example above, the property is `color` and the property value is `red`.

There must be a colon separating each property from its property value and a
semicolon at the end of the declaration. By adding just that rule to our CSS and
refreshing the page in the browser, we can see the effect of the rule.

## Break (10 minutes / 2:40)

### Properties (20 minutes / 3:00)

Like HTML elements, there are tons of css properties and it is impractical to
memorize them. Again we're looking for the 20% that gets us 80% of the way.

Here are some good ones to know:

#### [Background](https://developer.mozilla.org/en-US/docs/Web/CSS/background)

There is a ton we can do with the background of a page but for now we'll keep it
simple just setting its color to off white. Generally we will use a
[**hex-triplet**](https://en.wikipedia.org/wiki/Web_colors#Hex_triplet) to
describe colors.

Let's get back to building out our "Learn HTML" page.

In `style.css` replace the `lemonchiffon` background with:

```css
body {
  background: #F5F5F5;
}
```

**BONUS** just adding textures to a site's background can make a huge difference
as well. A great resource for free patterns is [Transparent
Textures](https://www.transparenttextures.com/)

#### [Text](https://developer.mozilla.org/en-US/docs/Learn/CSS/Styling_text/Fundamentals)

#### [Color](https://developer.mozilla.org/en-US/docs/Web/CSS/color)

The color property sets the color of text. An easy improvement to the default
styling is to set the text color to something just off black. For off black, we
will use `#444`.

In `style.css` add the declaration to the body rule:

```css
body {
  color: #444;
  background: #F5F5F5;
}
```

#### [Font Family](https://developer.mozilla.org/en-US/docs/Learn/CSS/Styling_text/Fundamentals#Font_families)

A less subtle change we can easily make is to use a font other than the default
`Times`.

See below for details on bringing in custom fonts from Google Fonts. For now
we'll just use some of the **web safe fonts** which are available by default in
almost every browser. Some web safe fonts are:

- Arial (sans-serif)
- Courier New (monospace)
- Courier (monospace)
- Georgia (serif)
- Palatino (serif)
- Times New Roman (serif)
- Trebuchet MS (sans-serif)
- Verdana (sans-serif)

Because there can be problems loading fonts, we provide the `font-family`
property fallbacks in a comma separated list. Also note that fonts with a space
in their name need to be surrounded in quotation marks.

Let's add a declaration to the rule on body setting the font-family to a
sans-serif font:

```css
body {
  color: #444;
  background: #F5F5F5;
  font-family: Helvetica, Arial, sans-serif;
}
```

Notice the lower-case, dash deliminated property (sometimes called spine-case)
naming convention for multi-word properties.

Let's also add a new ruleset that just applies to the `h1` and give that a
monospaced font.

```css
h1 {
  font-family: "Courier New", Courier, monospace;
}
```

#### [Font size](https://developer.mozilla.org/en-US/docs/Learn/CSS/Styling_text/Fundamentals#Font_size)

A very common mistake is to use a header with a larger number (e.g. `h4`) for
the smaller font. This is bad practice. Instead we want to use the heading with
the appropriate meaning and then style appropriately.

Let's use a slightly smaller font for our h1 than the default `32px`:

```css
h1 {
  font-family: "Courier New", Courier, monospace;
  font-size: 24px;
}
```

**Bonus** there's a lot more you can do with [Font
styling](https://developer.mozilla.org/en-US/docs/Learn/CSS/Styling_text/Fundamentals#Font_style_font_weight_text_transform_and_text_decoration)
like text decorations and shadows!

For more detail on units of measurement in CSS check out [this Values and Units
guide](https://developer.mozilla.org/en-US/docs/Learn/CSS/Introduction_to_CSS/Values_and_units).

#### Text Layout

##### [Text Alignment](https://developer.mozilla.org/en-US/docs/Learn/CSS/Styling_text/Fundamentals#Text_alignment)

Next we'll center our heading by adding a declaration setting the `text-align`
value:

```css
h1 {
  font-family: "Courier New", Courier, monospace;
  font-size: 24px;
  text-align: center;
}
```

Keep in mind the text-align property will only work on text. We will cover
layout of other elements below in the discussion of the box model.

#### [Line Height](https://developer.mozilla.org/en-US/docs/Learn/CSS/Styling_text/Fundamentals#Line_height)

The line height property sets the size of each line. The property value accepts
any unit but is frequently seen without a unit meaning relative to the size of
the font (i.e. `2` is double spaced, `1.5` is one and a half)

The default line height of `1` is a little squished. Let's up that to `1.2` by
adding a declaration to the `body` rule:

```css
body {
  color: #444;
  background: #F5F5F5;
  font-family: Helvetica, Arial, sans-serif;
  line-height: 1.3;
}
```

Note: We see the space following the headings growing but it would be much more
obvious with multiline paragraphs. When we need more text than we have, we can
use [Lorem Ipsum](http://www.lipsum.com/) as filler.

**BONUS** Similar to line-height, the [word-spacing and
letter-spacing](https://developer.mozilla.org/en-US/docs/Learn/CSS/Styling_text/Fundamentals#Letter_and_word_spacing)
properties can be used to adjust space around text.

Next we want to fix the obnoxiously large image but first we should review how
elements relate to space with the **box model**.

## Box Model: I do (10 minutes / 3:10)

The browser represents HTML elements on the page as blocks. Every block on the
page has `width`, `height`, `padding`, `margin`, and `border` properties. This
diagram shows how these values relate to one another.

![Box
Model](https://upload.wikimedia.org/wikipedia/commons/5/53/Css_box_model.svg)

We can see the box for an element on our page by right-clicking the element and
clicking **inspect**. Then find the **Styles** window, and scroll to the bottom.

Let's find our HTML5 logo from before and add a new rule for images:

```css
img {
  height: 300px;
}
```

Now let's change the `padding`, `border`, `margin`, and `background-color` and
**inspect** the image to see how these properties come into play.  

```css
img {
  height: 300px;
  background-color: green;
  padding: 20px;
  border: 5px solid black;
  margin: 50px;
}
```

## Taking Up Space: Inline vs Block Elements, and How to Center Content (10 minutes / 3:20)

Typically, elements are either **inline** or **block** elements.  We can change
this with the `display` property, and the four values we can assign it.  

![block](./images/block.png)

- A **block** element has some whitespace above and below it and does not
  tolerate any HTML elements next to it. This makes the element a block box. It
  won't let anything sit next to it on the page and takes up the full width. This is what most of the elements on a webpage are.

----

![inline](./images/inline.png)

- An **inline** element has no line break before or after it. This makes the
  element sit on the same line as another element, but without formatting it
  like a block. It only takes up as much width as it needs. Inline places all
  your elements on a single line. Padding / margins only work left + right, not top and bottom. Top and bottom spacing is controlled by line-height property because the content is inline.

----

![inline-block](./images/inline-block.png)

- An **inline-block** element is placed as an inline element (on the same line
  as adjacent content), but it behaves as a block element. This makes the
  element a block box but will allow other elements to sit next to it on the
  same line. You can move these with the text-align property, which is weird but useful.

----

- If you assign **none** as the value of the display, this will make the element
  and its content disappear from the page entirely!

To see this inline behavior we can create a few `div` tags to our **HTML**

```html
<div>Content</div>
<div>Content</div>
<div>Content</div>
```

And in our **CSS**...

```css
div {
  background-color: blue;
  border: 2px solid red;
  height: 30px;
}
```

The default display property for `div` is **block**.

Let's change the display property over to **inline**, and **inline-block** to
see the difference.

Here's a [codepen example of the
differences](https://codepen.io/jabyess/pen/NMLYBG) between
block, inline, and inline-block display properties.

### Centering text vs divs

- You can center text by setting the `text-align` property to `center`

- In order to center a block element, you can set the `margin` property to
  `auto`, or to something like `20px auto`.  This sets the left and right
  margins to be equal, putting the element in the center of the page.  

## More on Border, Padding, and Margin: You do (Bonus)

Let's use a margin, border, and padding to give the body a frame.

First we'll add a rule setting a dark grey background color for the `html`
element. This will be the backdrop to our frame.

```css
html {
  background: #222;
}
```

We immediately see the dark grey around the edges of the body. This is because
body by default has margins of 8px. We can increase this using the same margin
property we use on the `img`.

```css
body {
  color: #444;
  background: url("https://www.transparenttextures.com/patterns/45-degree-fabric-light.png"), #F5F5F5;
  font-family: Helvetica, Arial, sans-serif;
  line-height: 1.3;
  margin: 40px 60px;
}
```

Now let's add a border to the body:

```css
body {
  color: #444;
  background: url("https://www.transparenttextures.com/patterns/45-degree-fabric-light.png"), #F5F5F5;
  font-family: Helvetica, Arial, sans-serif;
  line-height: 1.3;
  margin: 40px 60px;
  border: 5px solid skyblue;
}
```

Checking out the result in the browser, the border is a bit heavy and the color
is a bit odd. Let's update the value of the border property:

```css
body {
  color: #444;
  background: url("https://www.transparenttextures.com/patterns/45-degree-fabric-light.png"), #F5F5F5;
  font-family: Helvetica, Arial, sans-serif;
  line-height: 1.3;
  margin: 40px 60px;
  border: 3px solid #e44d26;
}
```

Now that we have a border on the body, the last thing we want to address is the
contained texted jammed into the border. The `padding` property is similar to
the `margin` but instead of defining the space between the border and the
external elements, it defines the space between the border and the content.

We add padding to the body as follows:

```css
body {
  color: #444;
  background: url("https://www.transparenttextures.com/patterns/45-degree-fabric-light.png"), #F5F5F5;
  font-family: Helvetica, Arial, sans-serif;
  line-height: 1.3;
  margin: 40px 60px;
  border: 3px solid #e44d26;
  padding: 25px 30px;
}
```

Note: In this case we used the body as a visual container. Frequently we will
want more sub-containers for visual purposes. The generic block element used for
these purposes is the `div`. We'll see more `div`s when we talk about using
flex-box for advanced alignment.

There are many many more CSS properties and nearly no limit to what CSS will let
us do but these building blocks will take us a very long way. Check out the
significant difference just this bit of CSS has made.

## Importing Fonts: You do (Bonus)

Google hosts a massive repository of fonts that can be imported for use on your
page.

To add a font:

1. Go to [Google Fonts](https://fonts.google.com/)
2. Click the **+** button next to any font you want to import to your page (as a
   rule, any more than 3 fonts in a project quickly begins to look disjointed).
3. After selecting 1 or more fonts, click the bar on the bottom that says **1
   Family Selected**.
4. Add the provided link element (something like `<link
   href="https://fonts.googleapis.com/css?family=Fresca" rel="stylesheet">`) to
   the head of your HTML.
5. Add the provided declaration (something like `font-family: 'Fresca',
   sans-serif;`) to a CSS rule targeting the elements to which you would like to
   apply the font.

## Closing

There's a lot you can do with CSS! Don't underestimate it as just "colors and
spacing" - there are a lot of interesting and engaging CSS tricks that can
greatly improve the appearance of your webpage. In your next lecture, you'll
learn more about layout in CSS.

>To see an example of the power of CSS, check out this animation: [Kylo Ren
>CSS](https://tympanus.net/codrops/2017/10/31/star-wars-kylo-ren-x-pure-css-animation/)

> or this awesome set of drawings made using [A SINGLE DIV](https://a.singlediv.com/) and a bunch of CSS

> or this crazy complex [pure css art](https://github.com/cyanharlow/purecss-francine)

## Bonus Exercise: [Fashion Blog](https://git.generalassemb.ly/java-interapt-11-8/fashion-blog)

## Bonus Exercise: [Hippie Portfolio](https://git.generalassemb.ly/java-interapt-11-8/hippy-portfolio)

## Homework: [Wendy Bite](https://git.generalassemb.ly/java-interapt-11-8/wendy-bite)
