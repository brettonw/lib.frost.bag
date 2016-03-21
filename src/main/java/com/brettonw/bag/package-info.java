/**
 * Provides two container classes for text-based storage of constrained types in an array (BagArray)
 * or as a map (BagObject), with a means of serializing objects to and from these container types.
 * <p>
 * Stored values are constrained to primitive types or their boxed analog, strings, and other bags.
 * More complex types can be stored using the Serializer.
 * <p>
 * Type assignment is performed lazily on extraction, and presumes the user knows what they are
 * expecting to get.
 * <p>
 * These classes are primarily intended for messaging, events, and other applications that require
 * complex values to be shared in a text-based data interchange format without the formality of
 * declaring classes or establishing schemas.
 * <p>
 * Bag is loosely based on a combination of XML, JSON (from www.json.org), and various other
 * serializers. Sometimes you want JSON, sometimes you want a serializer, and sometimes you want to
 * move seamlessly between the two.
 * <p>
 * Bag is not meant to consume arbitrary JSON files, and it probably won't work for that. The text
 * format is generated using the "toString" and "fromString" operators, and that is all it is
 * currently spec'd to do, and all it is tested for.
 * <p>
 * The error handling philosophy is to return null and log failures. The user can choose to throw an
 * exception if they want, but Bag should be robust and continue chugging without killing the parent
 * application if an unanticipated fault happens.
 *
 * @author Bretton Wade
 */
package com.brettonw.bag;
