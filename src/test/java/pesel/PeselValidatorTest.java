package pesel;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.*;


public class PeselValidatorTest {

    @Test
    public void hasCorrectLengthDetectsShortStrings() {
        assertFalse(PeselValidator.hasCorrectLength("1234"));
    }

    @Test
    public void hasCorrectLengthDetectsLongStrings() {
        assertFalse(PeselValidator.hasCorrectLength("123456789101"));
    }

    @Test
    public void hasCorrectLengthDetectsCorrectStrings() {
        assertTrue(PeselValidator.hasCorrectLength("12345678910"));
    }

    @Test
    public void hasOnlyDigitsDetectsNonDigits() {
        assertFalse(PeselValidator.hasOnlyDigits("123abcd3f"));
    }

    @Test
    public void hasOnlyDigitsDetectsNumericalString() {
        assertTrue(PeselValidator.hasOnlyDigits("1234567"));
    }

    @Test
    public void hasCorrectChecksumDetectsCorrectChecksum() {
        assertTrue(PeselValidator.hasCorrectChecksum("00211369615"));
    }

    @Test
    public void hasCorrectChecksumDetectsIncorrectChecksum() {
        assertFalse(PeselValidator.hasCorrectChecksum("00211369613"));
    }

    @Test
    public void hasCorrectBirthDateDetectsIncorrectMonth() {
        assertFalse(PeselValidator.hasCorrectBirthDate("00131369615"));
    }

    @Test
    public void hasCorrectBirthDateDetectsIncorrectDayInMonth() {
        assertFalse(PeselValidator.hasCorrectBirthDate("00843169615"));
    }

    @Test
    public void hasCorrectBirthDateDetectsFebruaryInLeapYear() {
        assertFalse(PeselValidator.hasCorrectBirthDate("00022969615"));
        assertFalse(PeselValidator.hasCorrectBirthDate("89022969615"));
        assertTrue(PeselValidator.hasCorrectBirthDate("88022969615"));
        assertTrue(PeselValidator.hasCorrectBirthDate("00222969615"));
    }

    @Test
    public void hasCorrectBirthDateDetectsCorrectBirthDate() {
        assertTrue(PeselValidator.hasCorrectBirthDate("00211369613"));
    }

    @Test
    public void validatesCorrectly() {
        List<ValidationErrors> errors;
        errors = PeselValidator.validate("00211369615");
        assertTrue(errors.isEmpty());

        errors = PeselValidator.validate("002113696");
        assertEquals(1, errors.size());
        assertTrue(errors.contains(ValidationErrors.INVALID_LENGTH));

        assertThat(PeselValidator.validate("002113696"))
                .hasSize(1)
                .contains(ValidationErrors.INVALID_LENGTH)
                .doesNotContain(ValidationErrors.INVALID_CHECKSUM, ValidationErrors.INVALID_DATE);

        assertThat(PeselValidator.validate("0021ab36961"))
                .hasSize(1)
                .containsExactly(ValidationErrors.NOT_ONLY_DIGITS);

        assertThat(PeselValidator.validate("00211369614"))
                .hasSize(1)
                .containsExactly(ValidationErrors.INVALID_CHECKSUM);

        assertThat(PeselValidator.validate("20219369615"))
                .hasSize(1)
                .containsExactly(ValidationErrors.INVALID_DATE);
        assertThat(PeselValidator.validate("02541369614"))
                .hasSize(2)
                .containsExactly(ValidationErrors.INVALID_CHECKSUM,
                        ValidationErrors.INVALID_DATE);
    }
}