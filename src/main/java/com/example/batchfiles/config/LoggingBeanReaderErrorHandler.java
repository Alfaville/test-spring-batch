package com.example.batchfiles.config;


import lombok.extern.slf4j.Slf4j;
import org.beanio.*;

/**
 * A BeanIO {@code BeanReaderErrorHandler} that logs the error messages.
 *
 * @author Nico Schlebusch
 */
@Slf4j
public class LoggingBeanReaderErrorHandler extends BeanReaderErrorHandlerSupport {

    /**
     * Creates a new instance of {@code LoggingBeanReaderErrorHandler}.
     */
    public LoggingBeanReaderErrorHandler() {
    }

    /**
     * {@inheritDoc}
     *
     * @see org.beanio.BeanReaderErrorHandlerSupport#invalidRecord(org.beanio.InvalidRecordException)
     */
    @Override
    public void invalidRecord(final InvalidRecordException ex) throws BeanReaderException {
        log.error("{}", createErrorMessage(ex));
    }

    /**
     * {@inheritDoc}
     *
     * @see org.beanio.BeanReaderErrorHandlerSupport#unexpectedRecord(org.beanio.UnexpectedRecordException)
     */
    @Override
    public void unexpectedRecord(final UnexpectedRecordException ex) throws BeanReaderException {
        log.error("{}", createErrorMessage(ex));
    }

    /**
     * {@inheritDoc}
     *
     * @see org.beanio.BeanReaderErrorHandlerSupport#unidentifiedRecord(org.beanio.UnidentifiedRecordException)
     */
    @Override
    public void unidentifiedRecord(final UnidentifiedRecordException ex) throws BeanReaderException {
        log.error("{}", createErrorMessage(ex));
    }

    /**
     * {@inheritDoc}
     *
     * @see org.beanio.BeanReaderErrorHandlerSupport#malformedRecord(org.beanio.MalformedRecordException)
     */
    @Override
    public void malformedRecord(final MalformedRecordException ex) throws BeanReaderException {
        log.error("{}", createErrorMessage(ex));
    }

    /**
     * {@inheritDoc}
     *
     * @see org.beanio.BeanReaderErrorHandlerSupport#fatalError(org.beanio.BeanReaderException)
     */
    @Override
    public void fatalError(final BeanReaderException ex) throws BeanReaderException {
        log.error("{}", createErrorMessage(ex));
    }

    /**
     * Creates an error message using the exception to get the RecordContext from which a meaningful error
     * message can be constructed.
     *
     * @param ex the exception containing the error information.
     *
     * @return a string describing the error(s).
     */
    protected String createErrorMessage(final BeanReaderException ex) {

        final String message = ex.getMessage();
        final StringBuilder errorMessage = new StringBuilder(message.length() * 5);

        // if a bean object is mapped to a record group,
        // the exception may contain more than one record
        for (int i = 0, j = ex.getRecordCount(); i < j; i++) {
            final RecordContext recordContext = ex.getRecordContext(i);
            final String recordName = recordContext.getRecordName();
            final String text = recordContext.getRecordText();
            errorMessage.append(String.format("%s: %s%n", message, text));

            if (recordContext.hasRecordErrors()) {
                for (final String error : recordContext.getRecordErrors()) {
                    errorMessage.append(String.format("Record '%s' - %s%n", recordName, error));
                }
            }
            if (recordContext.hasFieldErrors()) {
                for (final String field : recordContext.getFieldErrors().keySet()) {
                    for (final String error : recordContext.getFieldErrors(field)) {
                        errorMessage.append(String.format("Field '%s' - %s%n", field, error));
                    }
                }
            }
        }

        return errorMessage.toString();
    }

}