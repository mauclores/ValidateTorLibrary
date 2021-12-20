/*
 * Modifications Copyright (c) 2018 Razeware LLC
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */

package com.raywenderlich.android.validatetorproject

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.widget.Toast
import com.raywenderlich.android.validatetor.ValidateTor

class MainActivity : AppCompatActivity() {

  private lateinit var validateTor: ValidateTor

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    setUpUiWidgets()
    validateTor = ValidateTor()
  }

  private fun setUpUiWidgets() {
    val btnValidate = findViewById<Button>(R.id.btn_validate)
    btnValidate.setOnClickListener {
      validateEmailField(findViewById(R.id.edt_email))
      validatePasswordField(findViewById(R.id.edt_password))
      validateCreditCardField(findViewById(R.id.edt_creditcard))
    }
  }

  private fun validateCreditCardField(editText: EditText) {
    val str = editText.text.toString()

    if (validateTor.isEmpty(str)) {
      editText.error = "Field is empty!"
    }

    if (!validateTor.validateCreditCard(str)) {
      editText.error = "Invalid credit card number!"
    } else {
      Toast.makeText(this, "Valid credit card number!", Toast.LENGTH_SHORT).show()
    }
  }

  private fun validatePasswordField(editText: EditText) {
    val str = editText.text.toString()

    if (validateTor.isEmpty(str)) {
      editText.error = "Field is empty!"
    }

    if (validateTor.isAtleastLength(str, 8) &&
        validateTor.hasAtleastOneDigit(str) &&
        validateTor.hasAtleastOneUppercaseCharacter(str) &&
        validateTor.hasAtleastOneSpecialCharacter(str)) {
      Toast.makeText(this, "Valid password!", Toast.LENGTH_SHORT).show()
    } else {
      editText.error = "Password needs to be a minimum length of 8 characters and " +
          "should have at least 1 digit, 1 uppercase letter, and 1 special character."
    }
  }

  private fun validateEmailField(editText: EditText) {
    val str = editText.text.toString()

    if (validateTor.isEmpty(str)) {
      editText.error = "Field is empty!"
    }

    if (!validateTor.isEmail(str)) {
      editText.error = "Invalid email!"
    } else {
      Toast.makeText(this, "Valid email!", Toast.LENGTH_SHORT).show()
    }
  }
}
